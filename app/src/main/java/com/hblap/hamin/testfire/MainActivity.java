package com.hblap.hamin.testfire;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    Button btn_dk, btn_dn;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.edt_email);
        pass = (EditText) findViewById(R.id.edt_pass);
        btn_dk = (Button) findViewById(R.id.btn_dk);
        btn_dn = (Button) findViewById(R.id.btn_dn);
        firebaseAuth = FirebaseAuth.getInstance();

        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString().trim() , pass.getText().toString().trim())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(MainActivity.this,"DK THANH CONG" , Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    Toast.makeText(MainActivity.this,"DK KHONG THANH CONG" , Toast.LENGTH_SHORT).show();
                                }
                                if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                                    Toast.makeText(getApplicationContext(), "Loi rò", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        btn_dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString().trim() , pass.getText().toString().trim())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(MainActivity.this,"DN THANH CONG" , Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(MainActivity.this,"DN KHONG THANH CONG" , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
