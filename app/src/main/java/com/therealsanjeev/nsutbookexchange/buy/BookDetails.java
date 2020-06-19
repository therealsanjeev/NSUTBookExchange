package com.therealsanjeev.nsutbookexchange.buy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.therealsanjeev.nsutbookexchange.R;
import com.therealsanjeev.nsutbookexchange.chat.chatBox;

public class BookDetails extends AppCompatActivity {

    Toolbar toolbar;
    TextView bookName,authName,price,name,number,email;
    private String id;
    RelativeLayout btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);
        setType();
        getData();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookDetails.this, chatBox.class);
                intent.putExtra("userId",id);
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
            id=bundle.getString("userId");
            name.setText((String)bundle.get("seller"));
//            number.setText((String)bundle.get("number"));
            email.setText((String)bundle.get("email"));
        }
    }

    private void setType() {
        btn=findViewById(R.id.chatBtnDetailBook);
        bookName=findViewById(R.id.tvBookNameDetails);
        authName=findViewById(R.id.tvAuthorNameDetails);
        price=findViewById(R.id.tvPriceDetails);
        name=findViewById(R.id.tvSellerNameDetails);
//        number=findViewById(R.id.tvSellerPhoneDetails);
        email=findViewById(R.id.tvSellerEmailDetails);

        //tool bar :
        toolbar=findViewById(R.id.toolBarOthers);
        toolbar.setTitle("Book Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}