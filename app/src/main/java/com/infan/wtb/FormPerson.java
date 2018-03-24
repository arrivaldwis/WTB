package com.infan.wtb;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FormPerson extends AppCompatActivity {

    SharedPreferences prefs = null;
    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_person);
        prefs = getSharedPreferences("preference", MODE_PRIVATE);

        //prefs.edit().putBoolean("profil", false).commit();

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.edit().putBoolean("profil", true).commit();
                prefs.edit().putString("nama_profil", "student").commit();
                Toast.makeText(FormPerson.this, "Profile Completed!!, You can choose the category", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.edit().putBoolean("profil", true).commit();
                prefs.edit().putString("nama_profil", "gamer").commit();
                Toast.makeText(FormPerson.this, "Profile Completed!!, You can choose the category", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.edit().putBoolean("profil", true).commit();
                prefs.edit().putString("nama_profil", "office").commit();
                Toast.makeText(FormPerson.this, "Profile Completed!!, You can choose the category", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.edit().putBoolean("profil", true).commit();
                prefs.edit().putString("nama_profil", "designer").commit();
                Toast.makeText(FormPerson.this, "Profile Completed!!, You can choose the category", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
