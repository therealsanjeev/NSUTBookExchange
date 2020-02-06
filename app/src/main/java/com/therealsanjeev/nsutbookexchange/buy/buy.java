package com.therealsanjeev.nsutbookexchange.buy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.therealsanjeev.nsutbookexchange.R;

import java.util.ArrayList;

public class buy extends AppCompatActivity {

    ArrayList<User> users;
    ListView lvAllData;
    BookAdapter bookAdapter;
    Toolbar toolbar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        users = new ArrayList<>();

        // tool Bar:
        toolbar=findViewById(R.id.toolBarOthers);
        toolbar.setTitle("Buy Books");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //list view & button id:
        lvAllData=findViewById(R.id.lvAlldata);

        //create adapter & set to listView :
        bookAdapter = new BookAdapter();
        lvAllData.setAdapter(bookAdapter);

        //get FireBase root address :
        final DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        db.child("user").addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                users.add(user);
                bookAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    class BookAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(users.isEmpty()){
                return 0;
            }
            return users.size();
        }

        @Override
        public User getItem(int position){
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView = getLayoutInflater().inflate(R.layout.book_listview, parent, false);

            TextView bookName = itemView.findViewById(R.id.tvBookName);
            TextView authorName = itemView.findViewById(R.id.tvAuthorName);
            TextView price = itemView.findViewById(R.id.tvPrice);
            TextView sellerName =itemView.findViewById(R.id.tvSellerName);
            TextView sellerNo = itemView.findViewById(R.id.tvSellerPhone);
            TextView sellerEmail = itemView.findViewById(R.id.tvSellerEmail);

            User us = getItem(position);

            bookName.setText(us.getBook().toUpperCase());
            authorName.setText(us.getAuthor().toUpperCase());
            price.setText(us.getPrice()+"₹");
            sellerName.setText(us.getSellerName().toUpperCase());
            sellerNo.setText(us.getSellerNo());
            sellerEmail.setText(us.getSellerEmail());

            return itemView;
        }
    }
}