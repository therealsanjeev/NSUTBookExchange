package com.therealsanjeev.nsutbookexchange.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.therealsanjeev.nsutbookexchange.MainActivity;
import com.therealsanjeev.nsutbookexchange.R;

public class RegisterActivity extends AppCompatActivity {

    EditText etRegEmail,etRegPass,etRegPassRepeat;
    Button btnSignUp;
    TextView tvSignUp;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tvSignUp=findViewById(R.id.tvsignup);

        etRegEmail=findViewById(R.id.etRegEmail);
        etRegPass=findViewById(R.id.etRegPass);
        etRegPassRepeat=findViewById(R.id.etRegPassRepeat);
        btnSignUp=findViewById(R.id.btnSignUp);

        auth=FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            fileList();
        }


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etRegEmail.getText().toString();
                String pass=etRegPass.getText().toString();
                String passRe=etRegPassRepeat.getText().toString();

                if(TextUtils.isEmpty(email)){
                    etRegEmail.setError("Email is Required.");
                    return;
                }if(TextUtils.isEmpty(pass)){
                    etRegPass.setError("Password id Required.");
                    return;
                }if(pass.length()<6){
                    etRegPass.setError("Password Must be >=6 Character.");
                    return;
                }
                if(!pass.toLowerCase().contains(passRe.toLowerCase())){
                    etRegPassRepeat.setError("Password Not Match.");
                    return;
                }

                //register Process:
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Welcome TO NSUT BOOK EXCHANGE",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this,"Error ! "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });




    }
}
