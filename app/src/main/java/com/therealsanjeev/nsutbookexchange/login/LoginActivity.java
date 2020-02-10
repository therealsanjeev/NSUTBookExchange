package com.therealsanjeev.nsutbookexchange.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.therealsanjeev.nsutbookexchange.MainActivity;
import com.therealsanjeev.nsutbookexchange.R;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginEmail,etLoginPass;
    Button btnLogin;
    TextView tvLogin;
    ProgressBar loginPro;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPro=findViewById(R.id.loginPro);
        etLoginEmail=findViewById(R.id.etLoginEmail);
        etLoginPass=findViewById(R.id.etLoginPass);
        btnLogin=findViewById(R.id.btnLogin);
        tvLogin=findViewById(R.id.tvNotSignIn);

        auth=FirebaseAuth.getInstance();
        loginPro.setVisibility(View.GONE);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etLoginEmail.getText().toString();
                String pass= etLoginPass.getText().toString();

                if(TextUtils.isEmpty(email)){
                    etLoginEmail.setError("Email is Required.");
                    return;
                }if(TextUtils.isEmpty(pass)){
                    etLoginPass.setError("Password id Required.");
                    return;
                }if(pass.length()<6){
                    etLoginPass.setError("Password Must be >=6 Character.");
                    return;
                }

                loginPro.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Welcome Back !",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this,"Error ! "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            loginPro.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });


    }
}
