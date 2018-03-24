package com.infan.wtb;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.infan.wtb.Model.BarangTypeModel;
import com.infan.wtb.Model.UserModel;

import java.util.ArrayList;

/**
 * Created by arrival on 3/24/18.
 */

public class App extends Application {
    public static ArrayList<BarangTypeModel> classificationResult = new ArrayList<>();
    public static int student = 0;
    public static int designer = 0;
    public static int office = 0;
    public static int gamer = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public static void dialogProfile(final Context context, SharedPreferences prefs) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_profile);

        LinearLayout linearLayout1 = (LinearLayout) dialog.findViewById(R.id.linearLayout1);
        ImageView btn2 = (ImageView) dialog.findViewById(R.id.btnEdit);
        final ImageView imageView1 = (ImageView) dialog.findViewById(R.id.imageView1);
        final TextView textView1 = (TextView) dialog.findViewById(R.id.textView1);
        final TextView txtEmail = (TextView) dialog.findViewById(R.id.txtEmail);
        final TextView txtFname = (TextView) dialog.findViewById(R.id.txtFname);
        final TextView txtLname = (TextView) dialog.findViewById(R.id.txtLname);
        final TextView txtHobby = (TextView) dialog.findViewById(R.id.txtHobby);
        Button btnLogout = (Button) dialog.findViewById(R.id.btnLogout);
        btnLogout.setVisibility(View.GONE);
        btn2.setVisibility(View.GONE);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");

        prefs = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
        final SharedPreferences finalPrefs = prefs;

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    UserModel model = ds.getValue(UserModel.class);
                    if(currentUser.getEmail().equals(model.getEmail())) {
                        if(finalPrefs.getBoolean("profil", false)){
                            String nama_profil = finalPrefs.getString("nama_profil", "");
                            textView1.setText(nama_profil);

                            txtEmail.setText(model.getEmail());
                            txtFname.setText(model.getFname());
                            txtLname.setText(model.getLname());
                            txtHobby.setText(model.getHobby());

                            if(nama_profil.contains("gamer")){
                                imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.gamecenter));
                            } else if(nama_profil.contains("student")){
                                imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.education_student));
                            } else if(nama_profil.contains("office")){
                                imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.briefcase));
                            } else if(nama_profil.contains("designer")){
                                imageView1.setImageDrawable(context.getResources().getDrawable(R.drawable.arts));
                            }
                        }else{
                            context.startActivity(new Intent(context, FormPerson.class));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        dialog.show();
    }
}
