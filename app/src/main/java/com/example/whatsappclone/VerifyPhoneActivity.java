package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.whatsappclone.databinding.ActivityVerifyPhoneBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyPhoneActivity extends AppCompatActivity {

   private ActivityVerifyPhoneBinding binding;
   private Intent intent;
   private Intent intent2;
   private String codeId;
   private FirebaseAuth auth;
   private String userPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyPhoneBinding.inflate(getLayoutInflater());
        intent = getIntent();
        auth = FirebaseAuth.getInstance();
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        codeId = intent.getStringExtra("CodeId");
        userPhone =auth.getCurrentUser().getPhoneNumber();

        binding.btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editOtp.getText().toString().isEmpty()){
                    binding.editOtp.setError("Enter OTP");
                }else {
                    verifyOtp(binding.editOtp.getText().toString());
                }
            }
        });
    }

    private void verifyOtp(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeId , otp);
        signIn(credential);
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void signIn(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                intent2 = new Intent(VerifyPhoneActivity.this , UserDataActivity.class);
                startActivity(intent2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(VerifyPhoneActivity.this , e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}