package com.therealsanjeev.nsutbookexchange.sell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.therealsanjeev.nsutbookexchange.R;
import com.therealsanjeev.nsutbookexchange.buy.model.User;

public class sell extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore fs;

    //button & EditText
    private Button btnReq;
    private EditText etBookName,etAuthor,etPrice,etSellerName,etSellerNo,etSellerEmail;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_books);

        auth=FirebaseAuth.getInstance();
        fs=FirebaseFirestore.getInstance();

        setType();
        takeInput();
    }

    private void takeInput() {
        final String book = etBookName.getText().toString();
        final String author = etAuthor.getText().toString();
        final String price = etPrice.getText().toString();
        final String sellerName = etSellerName.getText().toString();
        final String sellerNo = etSellerNo.getText().toString();
        final String sellerEmail = etSellerEmail.getText().toString();

        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get input from user :


                //add details on FireBase :
                DatabaseReference db =FirebaseDatabase.getInstance().getReference();
                if(book.length()!=0&&author.length()!=0&&price.length()!=0&&sellerName.length()!=0&&sellerNo.length()==10){

                    addBook(book,author,price,sellerName,sellerEmail,sellerNo);

                }else if(book.length()!=0&&author.length()!=0&&price.length()!=0&&sellerName.length()!=0&&sellerNo.length()!=10){
                    Toast.makeText(sell.this,"Invalid Phone No.",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(sell.this,"Invalid data!",Toast.LENGTH_LONG).show();
                }


            }
        });

    }



    private void addBook(String book,String author,String price,String sellerName,String sellerEmail,String sellerNo) {

        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();

        CollectionReference dc=fs.collection("Books").document(id).collection("request");
        User userData=new User(book,author,price,sellerName,sellerEmail,sellerNo);

        dc.add(userData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
//                            progressBar.setVisibility(View.INVISIBLE);
                etBookName.setText(null);
                etAuthor.setText(null);
                etPrice.setText(null);
                etSellerName.setText(null);
                etSellerNo.setText(null);
                etSellerEmail.setText(null);
                Toast.makeText(sell.this,"Your request Received, Thank You :)",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                            progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(sell.this,"Something is Wrong? Try Again...",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setType() {

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
    }

}
