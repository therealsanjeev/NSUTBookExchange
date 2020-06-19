package com.therealsanjeev.nsutbookexchange.chat.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.therealsanjeev.nsutbookexchange.R;
import com.therealsanjeev.nsutbookexchange.buy.model.User;
import com.therealsanjeev.nsutbookexchange.chat.model.chatUser;

import java.util.List;

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.ViewHolder> {

    private Context context;
    private List<chatUser> users;

    public chatAdapter(Context context, List<chatUser> users) {
        this.context = context;
        this.users = users;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chat_list,parent,false);

        return new chatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        chatUser user=users.get(position);
        holder.username.setText(user.getName());
        if(user.getImageURL().equals("default")){
            holder.profile.setImageResource(R.mipmap.ic_launcher);
            Glide.with(context).load(user.getImageURL()).into(holder.profile);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public ImageView profile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.chatUserName);
            profile=itemView.findViewById(R.id.chatProfile);
        }
    }
}
