package com.therealsanjeev.nsutbookexchange.buy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.therealsanjeev.nsutbookexchange.R;

import java.util.ArrayList;

public class buy extends AppCompatActivity {

    Button btnSearch;
    ArrayList<User> users;
    ListView lvAllData;
    BookAdapter bookAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        toolbar=findViewById(R.id.toolBarOthers);
        toolbar.setTitle("Buy Books");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvAllData=findViewById(R.id.lvAlldata);
        btnSearch=findViewById(R.id.btnSearch);

        bookAdapter = new BookAdapter();
        lvAllData.setAdapter(bookAdapter);

        final DatabaseReference db= FirebaseDatabase.getInstance().getReference();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.child("user").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        User user=dataSnapshot.getValue(User.class);
                        users.add(user);
                        bookAdapter.notifyDataSetChanged();
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
        });
    }
    class BookAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView=getLayoutInflater().inflate(
                    R.layout.book_listview,
                    parent,false
            );

            TextView bookName=itemView.findViewById(R.id.tvBookName);
            TextView authorName=itemView.findViewById(R.id.tvAuthorName);
            TextView price=itemView.findViewById(R.id.tvPrice);
            TextView sellerName=itemView.findViewById(R.id.tvSellerName);
            TextView sellerNo=itemView.findViewById(R.id.tvSellerPhone);
            TextView sellerEmail=itemView.findViewById(R.id.tvSellerEmail);

            User us = (User) getItem(position);

            bookName.setText(us.getBook());
            authorName.setText(us.getAuthor());
            price.setText(us.getPrice());
            sellerName.setText(us.getSellerName());
            sellerNo.setText(us.getSellerNo());
            sellerEmail.setText(us.getSellerEmail());

            return itemView;
        }
    }
}
