package com.infan.wtb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HP extends AppCompatActivity {

    Button btn1, btn2;
    ImageView btnProfile;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp);
        prefs = getSharedPreferences("preference",MODE_PRIVATE);

        btn1 = (Button) findViewById(R.id.btnAndroid);
        btn2 = (Button) findViewById(R.id.btnApple);
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
                App.dialogProfile(HP.this, prefs);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HP.this, ContentScreen.class);
                intent.putExtra("kategori_id", 13);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HP.this, ContentScreen.class);
                intent.putExtra("kategori_id", 14);
                startActivity(intent);
            }
        });
    }
}
