package com.therealsanjeev.nsutbookexchange.buy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.therealsanjeev.nsutbookexchange.R;

public class BookDetails extends AppCompatActivity {

    Toolbar toolbar;
    TextView bookName,authName,price,name,number,email;
    String phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);
        setType();
        getData();

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(phoneNo));
                startActivity(intent);
            }
        });
    }

    private void getData() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            bookName.setText((String) bundle.get("book"));
            authName.setText((String)bundle.get("auth"));
            price.setText("₹"+(String)bundle.get("price"));
            phoneNo=(String)bundle.get("seller");
            name.setText(phoneNo);
            number.setText((String)bundle.get("number"));
            email.setText((String)bundle.get("email"));
        }
    }

    private void setType() {

        bookName=findViewById(R.id.tvBookNameDetails);
        authName=findViewById(R.id.tvAuthorNameDetails);
        price=findViewById(R.id.tvPriceDetails);
        name=findViewById(R.id.tvSellerNameDetails);
        number=findViewById(R.id.tvSellerPhoneDetails);
        email=findViewById(R.id.tvSellerEmailDetails);

        //tool bar :
        toolbar=findViewById(R.id.toolBarOthers);
        toolbar.setTitle("Book Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}