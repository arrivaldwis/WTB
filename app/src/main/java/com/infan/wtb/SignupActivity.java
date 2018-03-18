package com.infan.wtb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnlogin;
    private EditText txtusername;
    private EditText txtpassword;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnlogin=(Button) findViewById(R.id.btnlogin);
        txtusername=(EditText) findViewById(R.id.txtusername);
        txtpassword=(EditText) findViewById(R.id.txtpassword);

        mDatabase = FirebaseDatabase.getInstance().getReference("User");
        mAuth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtusername.getText().toString().isEmpty()||txtpassword.getText().toString().isEmpty()){
                    Toast.makeText(SignupActivity.this, "Lengkapi Data Username dan Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String email = txtusername.getText().toString();
                String password = txtpassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Long id = System.currentTimeMillis();
                                    Toast.makeText(SignupActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }
        });
    }
}
