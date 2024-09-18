package com.example.whatsappclone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whatsappclone.ChatRecyclerView.Adapter.ChatAdapter;
import com.example.whatsappclone.ChatRecyclerView.Models.ChatModel;
import com.example.whatsappclone.ChatRecyclerView.Models.UserModel;
import com.example.whatsappclone.databinding.FragmentChatsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {

    private FragmentChatsBinding binding;
    private ArrayList<ChatModel>models;
    private ChatAdapter adapter;
    private ValueEventListener eventListener;
    private ArrayList<UserModel>users;
    private FirebaseDatabase database;
    private DatabaseReference ref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        models = new ArrayList<>();
        adapter = new ChatAdapter(getContext());
        users = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        eventListener =null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentChatsBinding.bind(view);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child("users").getChildren()){
                    UserModel userModel = dataSnapshot.getValue(UserModel.class);
                    if (userModel!=null){
                        users.add(userModel);
                    }

                }
                adapter.setData(users);
                binding.recyclerViewContainer.setAdapter(adapter);
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ChatsFragment.this.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        ref.addValueEventListener(eventListener);



    }
}