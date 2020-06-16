package com.therealsanjeev.nsutbookexchange.buy.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.therealsanjeev.nsutbookexchange.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView bookName,authorName,price,sellerName,sellerNo,sellerEmail;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView=itemView.findViewById(R.id.cardView);
        bookName = itemView.findViewById(R.id.tvBookName);
        authorName = itemView.findViewById(R.id.tvAuthorName);
        price = itemView.findViewById(R.id.tvPrice);
        sellerName =itemView.findViewById(R.id.tvSellerName);
        sellerNo = itemView.findViewById(R.id.tvSellerPhone);
        sellerEmail = itemView.findViewById(R.id.tvSellerEmail);
    }
}
