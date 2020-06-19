package com.therealsanjeev.nsutbookexchange.sell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.therealsanjeev.nsutbookexchange.R;
import com.therealsanjeev.nsutbookexchange.buy.model.User;

public class sell extends AppCompatActivity {

    FirebaseUser firebaseUser;

    //button & EditText
    Button btnReq;
    EditText etBookName,etAuthor,etPrice,etSellerName,etSellerNo,etSellerEmail;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_books);

        toolbar=findViewById(R.id.toolBarOthers);
        toolbar.setTitle("Sell Books");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnReq=findViewById(R.id.btnReq);
        etBookName=findViewById(R.id.etBookName);
        etAuthor=findViewById(R.id.etAuthor);
        etPrice=findViewById(R.id.etPrice);
        etSellerName=findViewById(R.id.etSellerName);
        etSellerNo=findViewById(R.id.etSellerNo);
        etSellerEmail=findViewById(R.id.etSellerEmail);

        addBook();

    }

    private void addBook() {
        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get input from user :
                String book = etBookName.getText().toString();
                String author = etAuthor.getText().toString();
                String price = etPrice.getText().toString();
                String sellerName = etSellerName.getText().toString();
                String sellerNo = etSellerNo.getText().toString();
                String sellerEmail = etSellerEmail.getText().toString();

                String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
                //add details on FireBase :
                DatabaseReference db =FirebaseDatabase.getInstance().getReference();
                if(book.length()!=0&&author.length()!=0&&price.length()!=0&&sellerName.length()!=0&&sellerNo.length()==10){
                    User user = new User(book,author,price,sellerName,sellerEmail,sellerNo,id);
                    db.child("user").push().setValue(user);
                    //remove after submit :
                    etBookName.setText(null);
                    etAuthor.setText(null);
                    etPrice.setText(null);
                    etSellerName.setText(null);
                    etSellerNo.setText(null);
                    etSellerEmail.setText(null);
                    Toast.makeText(sell.this,"Your request Received, Thank You :)",Toast.LENGTH_LONG).show();

                }else if(book.length()!=0&&author.length()!=0&&price.length()!=0&&sellerName.length()!=0&&sellerNo.length()!=10){
                    Toast.makeText(sell.this,"Invalid Phone No.",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(sell.this,"Invalid data!",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}
