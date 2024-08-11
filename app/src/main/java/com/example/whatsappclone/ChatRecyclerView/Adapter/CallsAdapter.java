package com.example.whatsappclone.ChatRecyclerView.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.ChatRecyclerView.Models.CallsModel;
import com.example.whatsappclone.R;

import java.util.ArrayList;

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.ViewHolder> {
    private ArrayList<CallsModel> data;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.callscard , parent ,false);
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

    public void setData(ArrayList<CallsModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        TextView name = itemView.findViewById(R.id.callTextName);
        TextView date = itemView.findViewById(R.id.callTextDate);
        private void bind(CallsModel model){
            name.setText(model.getName());
            date.setText(model.getDate());
        }
    }
}
