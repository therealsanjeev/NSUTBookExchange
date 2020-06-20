package com.therealsanjeev.nsutbookexchange.buy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.therealsanjeev.nsutbookexchange.R;
import com.therealsanjeev.nsutbookexchange.buy.BookDetails;
import com.therealsanjeev.nsutbookexchange.buy.buy;
import com.therealsanjeev.nsutbookexchange.buy.model.User;
import com.therealsanjeev.nsutbookexchange.buy.viewholder.ViewHolder;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<User> users;
    private Context context;

    public RecyclerViewAdapter(ArrayList<User> users,@NonNull Context context) {
        this.context=context;
        this.users=users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(
                R.layout.book_listview,
                parent,
                false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bookName.setText(users.get(position).getBook());
        ((ViewHolder)holder).authorName.setText(users.get(position).getAuthor());
        ((ViewHolder)holder).price.setText(users.get(position).getPrice());
        ((ViewHolder)holder).sellerName.setText(users.get(position).getSellerName());
        ((ViewHolder)holder).sellerEmail.setText(users.get(position).getSellerEmail());
//        ((ViewHolder)holder).sellerNo.setText(users.get(position).getSellerNo());
        setOnClick(((ViewHolder)holder).cardView,position);
    }

    private void setOnClick(CardView cardView, final int position) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user=users.get(position);

                Intent i=  new Intent(context, BookDetails.class);
                i.putExtra("book",users.get(position).getBook());
                i.putExtra("auth",users.get(position).getAuthor());
                i.putExtra("price",users.get(position).getPrice());
                i.putExtra("seller",users.get(position).getSellerName());
                i.putExtra("email",users.get(position).getSellerEmail());
//                i.putExtra("number",users.get(position).getSellerNo());
                i.putExtra("userId",user.getId());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
