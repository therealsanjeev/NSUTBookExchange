package com.therealsanjeev.nsutbookexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.therealsanjeev.nsutbookexchange.login.LoginActivity;
import com.therealsanjeev.nsutbookexchange.login.RegisterActivity;

public class homepage extends AppCompatActivity {

    RelativeLayout loginBtn;
    TextView registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        loginBtn = findViewById(R.id.login_button);
        registerBtn = findViewById(R.id.register_text_view);
        loginOnClick();
        registerOnClick();

    }


    private void registerOnClick() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage.this, RegisterActivity.class));
                Toast.makeText(homepage.this,"Registration", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loginOnClick() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage.this, LoginActivity.class));
            }
        });
    }
}