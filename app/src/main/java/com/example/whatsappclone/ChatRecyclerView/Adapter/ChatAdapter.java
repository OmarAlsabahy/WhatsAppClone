package com.example.whatsappclone.ChatRecyclerView.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsappclone.ChatRecyclerView.Models.UserModel;
import com.example.whatsappclone.MainActivity;
import com.example.whatsappclone.R;
import com.example.whatsappclone.UsersChatActivity;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{


    private ArrayList<UserModel> data ;
    private Context context;
    public  ChatAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatcard , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
        holder.onClick(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null){
            return 0;
        }else {
            return data.size();
        }
    }

    public void setData(ArrayList<UserModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        ImageView imageView = itemView.findViewById(R.id.profileImage);
        TextView name = itemView.findViewById(R.id.name);
        TextView chat = itemView.findViewById(R.id.chatText);
        TextView time = itemView.findViewById(R.id.timeText);

        public void bind(UserModel userModel) {
            Uri imageUri = Uri.parse(userModel.getImageUri());
            Glide.with(imageView).load(imageUri).into(imageView);
            name.setText(userModel.getUserName());
            chat.setText(userModel.getPhoneNumber());
            time.setText(System.currentTimeMillis() + "");
        }

        public void onClick(UserModel userModel) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UsersChatActivity.class);
                    intent.putExtra("name" , userModel.getUserName());
                    intent.putExtra("imageUri" , userModel.getImageUri());
                    intent.putExtra("userId" , userModel.getUserId());
                    intent.putExtra("phoneNumber" , userModel.getPhoneNumber());
                    context.startActivity(intent);
                }
            });
        }
    }
}
