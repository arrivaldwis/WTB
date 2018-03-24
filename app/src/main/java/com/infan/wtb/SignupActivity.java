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
import com.infan.wtb.Model.UserModel;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button btnDone;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtLname;
    private EditText txtFname;
    private EditText txtHobby;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnDone = (Button) findViewById(R.id.btnDone);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtLname = (EditText) findViewById(R.id.txtLname);
        txtFname = (EditText) findViewById(R.id.txtFname);
        txtHobby = (EditText) findViewById(R.id.txtHobby);

        mDatabase = FirebaseDatabase.getInstance().getReference("user");
        mAuth = FirebaseAuth.getInstance();

        btnDone.setOnClickListener(new View.OnClickListener() {
            String email = txtEmail.getText().toString();

            @Override
            public void onClick(View view) {
                if (txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Lengkapi Data Username dan Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                final String lname = txtLname.getText().toString();
                final String fname = txtFname.getText().toString();
                final String hobby = txtHobby.getText().toString();
                final String role = "user";

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                try {
                                    if (task.isSuccessful()) {
                                        try {
                                            Log.d("", "createUserWithEmail:success");
                                            mDatabase.push().setValue(new UserModel(email, fname, lname, hobby, role));
                                            Toast.makeText(SignupActivity.this, "Registrasi dan Login Berhasil", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } catch (Exception ex) {
                                            finish();
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("", "createUserWithEmail:" + task.getException(), task.getException());
                                        Toast.makeText(SignupActivity.this, "Authentication failed. " + task.getException(),
                                                Toast.LENGTH_LONG).show();

                                    }
                                } catch (Exception ex) {
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
