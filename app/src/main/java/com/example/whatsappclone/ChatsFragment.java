package com.example.whatsappclone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsappclone.ChatRecyclerView.Adapter.ChatAdapter;
import com.example.whatsappclone.ChatRecyclerView.Models.ChatModel;
import com.example.whatsappclone.databinding.FragmentChatsBinding;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {

    FragmentChatsBinding binding;
    ArrayList<ChatModel>models;
    ChatAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        models = new ArrayList<>();
        adapter = new ChatAdapter();
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentChatsBinding.bind(view);


        ///////RecyclerView/////////
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        models.add(new ChatModel("Omar","Hello","12:00"));
        adapter.setData(models);
        binding.recyclerViewContainer.setAdapter(adapter);

    }
}