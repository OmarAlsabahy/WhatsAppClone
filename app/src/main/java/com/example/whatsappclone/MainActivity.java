package com.example.whatsappclone;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.whatsappclone.ViewPager.Model;
import com.example.whatsappclone.ViewPager.ViewPagerAdapter;
import com.example.whatsappclone.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding ;
    ViewPagerAdapter adapter;
    ArrayList<Model>fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        fragments = new ArrayList<>();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Seeding FragmentsList
        fragments.add(new Model(new ChatsFragment() , getString(R.string.chatFragment)));
        fragments.add(new Model(new StatusFragment() , getString(R.string.statusFragment)));
        fragments.add(new Model(new CallsFragment() , getString(R.string.callsFragment)));
        adapter.setFragments(fragments);
        //Set adapter for viewPager
        binding.viewPager.setAdapter(adapter);
        //Bind TabLayout with ViewPager
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(fragments.get(position).getTitle());


            }
        }).attach();

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.viewPager);
                ChatsFragment chatsFragment = new ChatsFragment();

                if (fragment.equals(chatsFragment)){
                    finish();
                }
            }
        };

    }
}