package com.infan.wtb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PC extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    Button btn5,btn6,btn7,btn8;
    ImageView btnProfile;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc);
        prefs = getSharedPreferences("preference",MODE_PRIVATE);

        btn1 = (Button) findViewById(R.id.btnMobo);
        btn2 = (Button) findViewById(R.id.btnCPU);
        btn3 = (Button) findViewById(R.id.btnRam);
        btn4 = (Button) findViewById(R.id.btnStorage);
        btn5 = (Button) findViewById(R.id.btnCase);
        btn6 = (Button) findViewById(R.id.btnGpu);
        btn7 = (Button) findViewById(R.id.btnPsu);
        btn8 = (Button) findViewById(R.id.btnMonitor);
        btnProfile = (ImageView) findViewById(R.id.btnProfile);

        if(prefs.getBoolean("profil", false)){
            String nama_profil = prefs.getString("nama_profil", "");
            if(nama_profil.contains("gamer")){
                btnProfile.setImageDrawable(getResources().getDrawable(R.drawable.gamecenter));
            } else if(nama_profil.contains("student")){
                btnProfile.setImageDrawable(getResources().getDrawable(R.drawable.education_student));
            } else if(nama_profil.contains("office")){
                btnProfile.setImageDrawable(getResources().getDrawable(R.drawable.briefcase));
            } else if(nama_profil.contains("designer")){
                btnProfile.setImageDrawable(getResources().getDrawable(R.drawable.arts));
            }
        }


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.dialogProfile(PC.this, prefs);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PC.this, ContentScreen.class);
                intent.putExtra("kategori_id", 5);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PC.this, ContentScreen.class);
                intent.putExtra("kategori_id", 6);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PC.this, ContentScreen.class);
                intent.putExtra("kategori_id", 7);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PC.this, ContentScreen.class);
                intent.putExtra("kategori_id", 8);
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PC.this, ContentScreen.class);
                intent.putExtra("kategori_id", 9);
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PC.this, ContentScreen.class);
                intent.putExtra("kategori_id", 10);
                startActivity(intent);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PC.this, ContentScreen.class);
                intent.putExtra("kategori_id", 11);
                startActivity(intent);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PC.this, ContentScreen.class);
                intent.putExtra("kategori_id", 12);
                startActivity(intent);
            }
        });
    }
}
