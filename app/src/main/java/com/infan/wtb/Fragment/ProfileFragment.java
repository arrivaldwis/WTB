package com.infan.wtb.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.infan.wtb.ContentScreen;
import com.infan.wtb.FormPerson;
import com.infan.wtb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    SharedPreferences prefs = null;
    LinearLayout linearLayout1;
    ImageView imageView1, btn2;
    TextView textView1,textInfo;


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
        textInfo = (TextView) rootView.findViewById(R.id.textInformation);
        textView1 = (TextView) rootView.findViewById(R.id.textView1);
        prefs = getActivity().getSharedPreferences("preference", Context.MODE_PRIVATE);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FormPerson.class);
                startActivity(intent);
            }
        });
        selectData();
        return rootView;
    }

    private void selectData(){
        if(prefs.getBoolean("profil", false)){
            String nama_profil = prefs.getString("nama_profil", "");
            //Toast.makeText(getActivity(), nama_profil, Toast.LENGTH_SHORT).show();
            textView1.setText(nama_profil);
            //manggil profil di form person
            if(nama_profil.contains("gamer")){
                imageView1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.gamecenter));
                textInfo.setText(getString(R.string.information_gamer));
            } else if(nama_profil.contains("student")){
                imageView1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.education_student));
                textInfo.setText(getString(R.string.information_student));
            } else if(nama_profil.contains("office")){
                imageView1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.briefcase));
                textInfo.setText(getString(R.string.information_office));
            } else if(nama_profil.contains("designer")){
                imageView1.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.arts));
                textInfo.setText(getString(R.string.information_designer));
            }
        }else{
            startActivity(new Intent(getActivity(), FormPerson.class));
        }
    }

    @Override
    public void onResume() {
        selectData();
        super.onResume();
    }
}
