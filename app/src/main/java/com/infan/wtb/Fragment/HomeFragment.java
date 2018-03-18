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

import com.infan.wtb.ContentScreen;
import com.infan.wtb.FormPerson;
import com.infan.wtb.HP;
import com.infan.wtb.PC;
import com.infan.wtb.Peripheral;
import com.infan.wtb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    SharedPreferences prefs = null;
    Button btn1, btn2, btn3, btn4;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        prefs = getActivity().getSharedPreferences("preference", Context.MODE_PRIVATE);

        btn1 = (Button) rootView.findViewById(R.id.btnLaptop);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prefs.getBoolean("profil", false)){
                    Intent intent = new Intent(getActivity(), ContentScreen.class);
                    intent.putExtra("kategori_id", 1);
                    startActivity(intent);
                }else{
                    startActivity(new Intent(getActivity(), FormPerson.class));
                }
            }
        });

        btn2 = (Button) rootView.findViewById(R.id.btnPc);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prefs.getBoolean("profil", false)){
                    startActivity(new Intent(getActivity(), PC.class));
                }else{
                    startActivity(new Intent(getActivity(), FormPerson.class));
                }
            }
        });

        btn3 = (Button) rootView.findViewById(R.id.btnHp);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prefs.getBoolean("profil", false)){
                    Intent intent = new Intent(getActivity(), HP.class);
                    intent.putExtra("kategori_id", 3);
                    startActivity(intent);
                }else{
                    startActivity(new Intent(getActivity(), FormPerson.class));
                }
            }
        });
        btn4 = (Button) rootView.findViewById(R.id.btnPeri);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prefs.getBoolean("profil", false)){
                    Intent intent = new Intent(getActivity(), Peripheral.class);
                    intent.putExtra("kategori_id", 4);
                    startActivity(intent);
                }else{
                    startActivity(new Intent(getActivity(), FormPerson.class));
                }
            }
        });
        return rootView;
    }

}
