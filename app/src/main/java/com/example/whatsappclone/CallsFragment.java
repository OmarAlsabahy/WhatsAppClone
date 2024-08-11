package com.example.whatsappclone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsappclone.ChatRecyclerView.Adapter.CallsAdapter;
import com.example.whatsappclone.ChatRecyclerView.Models.CallsModel;
import com.example.whatsappclone.databinding.FragmentCallsBinding;

import java.util.ArrayList;


public class CallsFragment extends Fragment {

    FragmentCallsBinding binding;
    ArrayList<CallsModel> data;
    CallsAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calls, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCallsBinding.bind(view);
        data = new ArrayList<>();
        adapter = new CallsAdapter();
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        data.add(new CallsModel("Omar" , "18 july, 7:45 am"));
        adapter.setData(data);
        binding.callsRecyclerView.setAdapter(adapter);

    }
}