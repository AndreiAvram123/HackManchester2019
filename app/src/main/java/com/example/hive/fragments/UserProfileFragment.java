package com.example.hive.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.adapters.MainRecyclerAdapter;
import com.example.hive.model.CustomDivider;
import com.example.hive.model.Skill;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    private RecyclerView recyclerView;
    private MainRecyclerAdapter mainRecyclerAdapter;
    private ImageView closeButton;

   public static UserProfileFragment newInstance(){
       return new UserProfileFragment();
   }
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_user_profile,container,false);
        closeButton = layout.findViewById(R.id.close_button_profile);
        closeButton.setOnClickListener(view -> getFragmentManager().popBackStack());
        initializeRecyclerView(layout);
        return layout;
    }
    private void initializeRecyclerView(View layout) {
        ArrayList<Skill> skills =new ArrayList<>();
        skills.add(new Skill("Learning to sing","Advanced","I am going to learfdfd"));
        skills.add(new Skill("Learning to sing","Advanced","I am going to learfdfd"));
        skills.add(new Skill("Learning to sing","Advanced","I am going to learfdfd"));
        skills.add(new Skill("Learning to sing","Advanced","I am going to learfdfd"));
        recyclerView = layout.findViewById(R.id.recycler_view_profile);
        mainRecyclerAdapter = new MainRecyclerAdapter(skills);
        recyclerView.setAdapter(mainRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new CustomDivider(15));
        recyclerView.setHasFixedSize(true);
    }

}
