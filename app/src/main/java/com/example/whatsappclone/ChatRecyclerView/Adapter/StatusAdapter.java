package com.example.whatsappclone.ChatRecyclerView.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.ChatRecyclerView.Models.StatusModel;
import com.example.whatsappclone.R;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {

    private ArrayList<StatusModel> data;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statuscard , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data==null){
            return 0;
        }else {
            return data.size();
        }
    }

    public void setData(ArrayList<StatusModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        TextView name = itemView.findViewById(R.id.textView_name);

        private void bind(StatusModel model){
            name.setText(model.getName());
        }
    }
}
