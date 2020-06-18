package com.therealsanjeev.nsutbookexchange.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.therealsanjeev.nsutbookexchange.R;

public class chatUsers extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_users);
        toolbar=findViewById(R.id.toolBarOthers);

        toolbar.setTitle("Chats");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}