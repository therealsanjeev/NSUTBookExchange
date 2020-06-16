package com.therealsanjeev.nsutbookexchange.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.therealsanjeev.nsutbookexchange.MainActivity;
import com.therealsanjeev.nsutbookexchange.R;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginEmail,etLoginPass;
    RelativeLayout btnLogin;
    ProgressBar loginPro;
    FirebaseAuth auth;
    private CardView login_button_card_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setType();
        loginOnClick();
        inputChange();

    }

    private void setType() {
//        loginPro=findViewById(R.id.loginPro);
        etLoginEmail = findViewById(R.id.email);
        etLoginPass = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login_button);
        login_button_card_view = findViewById(R.id.login_button_card_view);
        auth = FirebaseAuth.getInstance();
//        loginPro.setVisibility(View.GONE);
    }

    private void loginOnClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etLoginEmail.getText().toString();
                String pass = etLoginPass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    etLoginEmail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    etLoginPass.setError("Password id Required.");
                    return;
                }
                if (pass.length() < 6) {
                    etLoginPass.setError("Password Must be >=6 Character.");
                    return;
                }

//                loginPro.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Welcome Back !", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                            loginPro.setVisibility(View.GONE);
                        }
                    }
                });


            }
        });

    }

    @SuppressLint("ResourceType")
    private void loginButtonStyle (){
        if (etLoginPass.getText().length() > 0 && etLoginEmail.getText().length() > 0) {
            if (!btnLogin.isFocusable()) {
                btnLogin.setFocusable(true);
                btnLogin.setClickable(true);
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
                TypedValue outValue = new TypedValue();
                getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                btnLogin.setBackgroundResource(outValue.resourceId);
            }
        } else {
            if (btnLogin.isFocusable()) {
                btnLogin.setFocusable(false);
                btnLogin.setClickable(false);
                login_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCardViewBackground)));
                btnLogin.setBackgroundResource(0);
            }
        }
    }

    private void inputChange() {
        etLoginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                loginButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etLoginPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                loginButtonStyle();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
