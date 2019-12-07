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
import com.example.hive.adapters.AdapterLearn;
import com.example.hive.model.CustomDivider;
import com.example.hive.model.Skill;

import java.util.ArrayList;


public class FragmentLearn extends Fragment {

    private AdapterLearn mainRecyclerAdapter  = new AdapterLearn();
    private static final String KEY_SKILLS_TO_LEARN = "KEY_SKILLS_TO_LEARN";

    public static FragmentLearn newInstance(@NonNull ArrayList<Skill> skillsToLearn){
        FragmentLearn fragmentLearn = new FragmentLearn();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_SKILLS_TO_LEARN,skillsToLearn);
        fragmentLearn.setArguments(bundle);
        return fragmentLearn;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<Skill> skillsToTeach = getArguments().getParcelableArrayList(KEY_SKILLS_TO_LEARN);
        mainRecyclerAdapter.addSkills(skillsToTeach);
    }

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
        RecyclerView recyclerView = layout.findViewById(R.id.recyler_view_learn);
        recyclerView.setAdapter(mainRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new CustomDivider(15));
        recyclerView.setHasFixedSize(true);
    }

}
