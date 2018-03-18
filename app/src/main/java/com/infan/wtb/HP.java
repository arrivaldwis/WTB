package com.infan.wtb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HP extends AppCompatActivity {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hp);

        btn1 = (Button) findViewById(R.id.btnAndroid);
        btn2 = (Button) findViewById(R.id.btnApple);

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
