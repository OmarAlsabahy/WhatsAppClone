package com.example.whatsappclone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.whatsappclone.ChatRecyclerView.Models.UserModel;
import com.example.whatsappclone.databinding.ActivityUserDataBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UserDataActivity extends AppCompatActivity {
    private ActivityUserDataBinding binding;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private Uri imageUri;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDataBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel user = new UserModel(
                        binding.editUsername.getText().toString().trim(),
                        imageUri.toString(),
                        auth.getCurrentUser().getUid(),
                        auth.getCurrentUser().getPhoneNumber()
                );

                DatabaseReference databaseReference = database.getReference().child("users")
                        .child(auth.getCurrentUser().getUid());
                databaseReference.setValue(user);
                startActivity(new Intent(UserDataActivity.this , MainActivity.class));
                finish();
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent , 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if(data!=null){
                Uri uri = data.getData();
                binding.userImage.setImageURI(uri);
                uploadImage(auth.getCurrentUser().getUid() , uri);
            }
        }
    }

    private void uploadImage(String uid, Uri uri) {
        if (uri!=null){
            StorageReference reference = storage.getReference().child("userImages/" + uid);
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imageUri = uri;
                            binding.btnSubmit.setEnabled(true);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UserDataActivity.this , "Failed To UploadImage" , Toast.LENGTH_SHORT).show();
                            Log.i("tesstError" , e.getMessage());
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UserDataActivity.this , "Failed To UploadImage" , Toast.LENGTH_SHORT).show();
                    Log.i("tesstError" , e.getMessage());
                }
            });
        }
    }

}
