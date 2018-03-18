package com.infan.wtb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Peripheral extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peripheral);

        btn1 = (Button) findViewById(R.id.btnMouse);
        btn2 = (Button) findViewById(R.id.btnKeyboard);
        btn3 = (Button) findViewById(R.id.btnHeadset);
        btn4 = (Button) findViewById(R.id.btnMousepad);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Peripheral.this, ContentScreen.class);
                intent.putExtra("kategori_id", 15);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Peripheral.this, ContentScreen.class);
                intent.putExtra("kategori_id", 16);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Peripheral.this, ContentScreen.class);
                intent.putExtra("kategori_id", 17);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Peripheral.this, ContentScreen.class);
                intent.putExtra("kategori_id", 18);
                startActivity(intent);
            }
        });
    }
}
