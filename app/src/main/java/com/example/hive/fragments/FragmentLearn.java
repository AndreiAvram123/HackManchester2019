package com.example.hive.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.adapters.MainRecyclerAdapter;
import com.example.hive.model.CustomDivider;
import com.example.hive.model.Skill;


public class FragmentLearn extends Fragment {

    RecyclerView recyclerView;
     MainRecyclerAdapter mainRecyclerAdapter  = new MainRecyclerAdapter();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_fragment_learn,container,false);
         initializeRecyclerView(view);
        return view;
    }
    public void addInterest(Skill skill){
        mainRecyclerAdapter.addSkill(skill);
    }
    private void initializeRecyclerView(View layout) {
        recyclerView = layout.findViewById(R.id.recyler_view_learn);
        recyclerView.setAdapter(mainRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new CustomDivider(15));
        recyclerView.setHasFixedSize(true);
    }

}
