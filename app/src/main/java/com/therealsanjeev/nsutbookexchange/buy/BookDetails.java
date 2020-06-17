package com.therealsanjeev.nsutbookexchange.buy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.therealsanjeev.nsutbookexchange.R;

public class BookDetails extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);
        setType();
    }

    private void setType() {
        toolbar=findViewById(R.id.toolBarOthers);
        toolbar.setTitle("Book Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}