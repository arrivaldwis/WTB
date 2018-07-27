package com.infan.wtb.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.infan.wtb.ContentScreen;
import com.infan.wtb.FormPerson;
import com.infan.wtb.LoginActivity;
import com.infan.wtb.Model.UserModel;
import com.infan.wtb.R;
import com.infan.wtb.SignupActivity;


public class ProfileFragment extends Fragment {

    SharedPreferences prefs = null;
    LinearLayout linearLayout1;
    ImageView imageView1, btn2;
    TextView textView1, txtEmail, txtFname, txtLname, txtHobby;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference mDatabase;
    Button btnLogout;
    Button btnVerifiy, btnForgot;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(){
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        linearLayout1 = (LinearLayout) rootView.findViewById(R.id.linearLayout1);
        btn2 = (ImageView) rootView.findViewById(R.id.btnEdit);
        imageView1 = (ImageView) rootView.findViewById(R.id.imageView1);
        textView1 = (TextView) rootView.findViewById(R.id.textView1);
        txtEmail = (TextView) rootView.findViewById(R.id.txtEmail);
        txtFname = (TextView) rootView.findViewById(R.id.txtFname);
        txtLname = (TextView) rootView.findViewById(R.id.txtLname);
        txtHobby = (TextView) rootView.findViewById(R.id.txtHobby);
        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
        btnVerifiy = (Button) rootView.findViewById(R.id.btnVerifiy);
        btnForgot = (Button) rootView.findViewById(R.id.btnForgot);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("user");

        try {
            checkLogin();
        } catch (Exception ex) {
            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        prefs = getActivity().getSharedPreferences("preference", Context.MODE_PRIVATE);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormPerson.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        try {
            if (currentUser.isEmailVerified()) {
                btnVerifiy.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
        }

        btnVerifiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        return rootView;
    }

    private void sendEmail() {
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait");
        pDialog.show();

        currentUser.sendEmailVerification().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task task) {
                // Re-enable button
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Verification email sent to " + currentUser.getEmail(),
                            Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();

                } else {
                    Toast.makeText(getActivity(), "Failed to send verification email.",
                            Toast.LENGTH_SHORT).show();
                    pDialog.dismiss();
                }
            }
        });
    }

    private void checkLogin() {
        if(currentUser==null) {
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        } else {
            selectData();
        }
    }

    private void selectData(){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    UserModel model = ds.getValue(UserModel.class);
                    if(currentUser.getEmail().equals(model.getEmail())) {
                        if(prefs.getBoolean("profil", false)){
                            String nama_profil = prefs.getString("nama_profil", "");
                            textView1.setText(nama_profil);

                            txtEmail.setText(model.getEmail());
                            txtFname.setText(model.getFname());
                            txtLname.setText(model.getLname());
                            txtHobby.setText(model.getHobby());

                            try {
                                if (nama_profil.contains("gamer")) {
                                    imageView1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.gamecenter));
                                } else if (nama_profil.contains("student")) {
                                    imageView1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.education_student));
                                } else if (nama_profil.contains("office")) {
                                    imageView1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.briefcase));
                                } else if (nama_profil.contains("designer")) {
                                    imageView1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.arts));
                                }
                            }catch (Exception ex) {}
                        }else{
                            startActivity(new Intent(getActivity(), FormPerson.class));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onResume() {
        //selectData();
        super.onResume();
    }
}
