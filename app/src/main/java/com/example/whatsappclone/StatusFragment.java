package com.example.whatsappclone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsappclone.ChatRecyclerView.Adapter.StatusAdapter;
import com.example.whatsappclone.ChatRecyclerView.Models.StatusModel;
import com.example.whatsappclone.databinding.FragmentStatusBinding;

import java.util.ArrayList;


public class StatusFragment extends Fragment {
    FragmentStatusBinding binding;
    ArrayList<StatusModel> data;
    StatusAdapter adapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentStatusBinding.bind(view);
        data = new ArrayList<>();
        adapter = new StatusAdapter();
        data.add(new StatusModel("Omar"));
        data.add(new StatusModel("Omar"));
        data.add(new StatusModel("Omar"));
        data.add(new StatusModel("Omar"));
        data.add(new StatusModel("Omar"));
        data.add(new StatusModel("Omar"));
        data.add(new StatusModel("Omar"));
        data.add(new StatusModel("Omar"));
        data.add(new StatusModel("Omar"));
        adapter.setData(data);
        binding.statusRecyclerView.setAdapter(adapter);
    }
}