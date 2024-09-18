package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.whatsappclone.ChatRecyclerView.Models.MessageModel;
import com.example.whatsappclone.ChatRecyclerView.Models.UserModel;
import com.example.whatsappclone.databinding.ActivityUsersChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class UsersChatActivity extends AppCompatActivity {

    private ActivityUsersChatBinding binding;
    private Intent intent;
    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private String recieverId;
    private ChildEventListener childEventListener;
    private List<MessageModel> messages;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersChatBinding.inflate(getLayoutInflater());
        intent = getIntent();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        recieverId = intent.getStringExtra("userId");
        messages = new ArrayList<>();
        databaseReference = database.getReference("messages");
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        UserModel user = new UserModel(
                intent.getStringExtra("name"),
                intent.getStringExtra("imageUri"),
                recieverId,
                intent.getStringExtra("phoneNumber")
        );
        binding.name.setText(user.getUserName());
        Glide.with(binding.profileImage).load(user.getImageUri()).into(binding.profileImage);

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        recieveMessages();

    }

    private void recieveMessages() {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MessageModel message = snapshot.getValue(MessageModel.class);
                if (message!=null){
                    messages.add(message);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        databaseReference.child(auth.getCurrentUser().getUid() + recieverId)
                .addChildEventListener(childEventListener);
    }


    private void sendMessage() {
        if (!binding.message.getText().toString().trim().isEmpty()) {
            MessageModel messageModel = new MessageModel(
                    auth.getCurrentUser().getUid(),
                    binding.message.getText().toString().trim(),
                   System.currentTimeMillis()+"",
                    recieverId,
                    databaseReference.push().getKey(),
                    auth.getCurrentUser().getUid()+recieverId
            );

           databaseReference.child(messageModel.getMessageId()).child(auth.getCurrentUser().getUid() + recieverId)
                            .setValue(messageModel);
            binding.message.setText("");
        }
    }



}