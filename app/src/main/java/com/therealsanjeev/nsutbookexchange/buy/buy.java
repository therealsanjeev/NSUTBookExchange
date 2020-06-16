package com.therealsanjeev.nsutbookexchange.buy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.therealsanjeev.nsutbookexchange.R;
import com.therealsanjeev.nsutbookexchange.buy.adapter.RecyclerViewAdapter;
import com.therealsanjeev.nsutbookexchange.buy.model.User;

import java.util.ArrayList;

public class buy extends AppCompatActivity {

    ArrayList<User> users;
    ListView lvAllData;
    BookAdapter bookAdapter;
    Toolbar toolbar;
    ProgressBar progressBar;
    ImageButton searchBtn;
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_books);

        setType();
        searchBook();


        bookAdapter = new BookAdapter(users);
        lvAllData.setAdapter(bookAdapter);
        fireBaseCall();

    }

    private void setAdapter() {

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(users,buy.this);
        recyclerView.setAdapter(adapter);

    }

    private void fireBaseCall() {

        //get FireBase root address :
        final DatabaseReference db= FirebaseDatabase.getInstance().getReference();
        db.child("user").addChildEventListener(new ChildEventListener(){
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                users.add(user);
                setAdapter();
//                bookAdapter.notifyDataSetChanged();
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

    private void searchBook() {
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String srcText=searchText.getText().toString();

                ArrayList<User> srcList=new ArrayList<>();
                BookAdapter adapter=new BookAdapter(srcList);

                for(User obj:users){
                    if(obj.getBook().toLowerCase().contains(srcText.toLowerCase())){
                        srcList.add(obj);
                    }
                }
                lvAllData.setAdapter(adapter);

            }
        });
    }

    private void setType() {
        users = new ArrayList<>();
        //id's
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        searchBtn =findViewById(R.id.searchButton);
        searchText=findViewById(R.id.searchEt);
        lvAllData=findViewById(R.id.lvAlldata);

        // tool Bar:
        toolbar=findViewById(R.id.toolBarOthers);
        toolbar.setTitle("Buy Books");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    class BookAdapter extends BaseAdapter {

        ArrayList<User> list;

        public BookAdapter(ArrayList<User> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public User getItem(int position){
            return list.get(position);
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
            price.setText("₹ "+us.getPrice());
            sellerName.setText(us.getSellerName().toUpperCase());
            sellerNo.setText(us.getSellerNo());
            sellerEmail.setText(us.getSellerEmail());

            return itemView;
        }
    }
}