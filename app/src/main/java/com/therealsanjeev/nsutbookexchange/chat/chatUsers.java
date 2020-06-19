package com.therealsanjeev.nsutbookexchange.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.therealsanjeev.nsutbookexchange.R;

import java.util.ArrayList;
import java.util.List;

public class chatUsers extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    List<chatUsers> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_users);
        toolbar=findViewById(R.id.toolBarOthers);

        toolbar.setTitle("Chats");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.chatRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        

    }
}