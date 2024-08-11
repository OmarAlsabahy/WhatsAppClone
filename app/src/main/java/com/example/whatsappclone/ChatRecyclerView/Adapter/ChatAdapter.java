package com.example.whatsappclone.ChatRecyclerView.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.ChatRecyclerView.Models.ChatModel;
import com.example.whatsappclone.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{


    private ArrayList<ChatModel> data ;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chatcard , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(data.get(position));

    }

    @Override
    public int getItemCount() {
       if(data==null){
           return 0;
       }else {
           return data.size();
       }
    }

    public void setData(ArrayList<ChatModel> data) {
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
        private void bind(ChatModel model){
            name.setText(model.getName());
            chat.setText(model.getChat());
            time.setText(model.getTime());
        }

    }
}
