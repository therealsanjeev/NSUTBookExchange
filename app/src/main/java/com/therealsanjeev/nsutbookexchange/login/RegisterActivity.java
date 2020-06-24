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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.therealsanjeev.nsutbookexchange.MainActivity;
import com.therealsanjeev.nsutbookexchange.R;

public class RegisterActivity extends AppCompatActivity {

    EditText etRegEmail, etRegPass, etRegPassRepeat, etRegName, etRegPhone;
    RelativeLayout btnSignUp;
    TextView loignBtn;
    FirebaseAuth auth;
    FirebaseUser authUser;
    ProgressBar regPro;
    private CardView signUp_button_card_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        setType();
        inputChange();
        onSignUpBtnClicked();


        loignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


    }

    private void onSignUpBtnClicked() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etRegEmail.getText().toString();
                String pass = etRegPass.getText().toString();
                String passRe = etRegPassRepeat.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    etRegEmail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    etRegPass.setError("Password id Required.");
                    return;
                }
                if (pass.length() < 6) {
                    etRegPass.setError("Password Must be >=6 Character.");
                    return;
                }
                if (!pass.toLowerCase().contains(passRe.toLowerCase())) {

                    etRegPassRepeat.setError("Password Not Match.");
                    return;
                }


                regPro.setVisibility(View.VISIBLE);

                //register Process:
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Welcome TO NSUT BOOK EXCHANGE", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            regPro.setVisibility(View.GONE);
                            finish();
                        } else {
                            regPro.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void setType() {
        signUp_button_card_view=findViewById(R.id.signup_button_card_view);
        loignBtn = findViewById(R.id.tvsignup);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPass = findViewById(R.id.etRegPass);
        etRegPassRepeat = findViewById(R.id.etRegPassRepeat);
        etRegName = findViewById(R.id.etRegName);
        etRegPhone = findViewById(R.id.etRegPhone);
        regPro = findViewById(R.id.regPro);
        btnSignUp = findViewById(R.id.btnSignUp);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        regPro.setVisibility(View.GONE);
    }


    private void inputChange() {
        etRegName.addTextChangedListener(new TextWatcher() {
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

        etRegEmail.addTextChangedListener(new TextWatcher() {
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
        etRegPhone.addTextChangedListener(new TextWatcher() {
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

        etRegPass.addTextChangedListener(new TextWatcher() {
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
        etRegPassRepeat.addTextChangedListener(new TextWatcher() {
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

    @SuppressLint("ResourceType")
    private void loginButtonStyle() {

        if (etRegName.getText().length()>0 &&etRegPhone.getText().length()==10 && etRegPass.getText().length() >= 8 && etRegEmail.getText().length() > 5) {
            if (!btnSignUp.isFocusable()) {
                btnSignUp.setFocusable(true);
                btnSignUp.setClickable(true);
                signUp_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorAccent)));
                TypedValue outValue = new TypedValue();
                getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
                btnSignUp.setBackgroundResource(outValue.resourceId);
            }
        } else {
            if (btnSignUp.isFocusable()) {
                btnSignUp.setFocusable(false);
                btnSignUp.setClickable(false);
                signUp_button_card_view.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCardViewBackground)));
                btnSignUp.setBackgroundResource(0);
            }
        }

    }
}
