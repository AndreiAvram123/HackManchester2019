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
import com.example.hive.adapters.AdapterTeach;
import com.example.hive.model.CustomDivider;
import com.example.hive.model.Skill;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TeachFragment extends Fragment {


    private TeachFragmentInterface teachFragmentInterface;
    private FloatingActionButton addTeachButton;
    private RecyclerView recyclerView;
    private AdapterTeach mainRecyclerAdapter = new AdapterTeach();
    private static final String KEY_SKILLS_TO_TEACH = "KEY_SKILLS_TO_TEACH";

    public static TeachFragment newInstance(@NonNull ArrayList<Skill> skillsToTeach) {
        TeachFragment teachFragment = new TeachFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_SKILLS_TO_TEACH,skillsToTeach);
        teachFragment.setArguments(bundle);
        return teachFragment;
    }


    public void addSkillToAdapter(Skill skill) {
        mainRecyclerAdapter.addSkill(skill);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        teachFragmentInterface = (TeachFragmentInterface) getActivity();
        ArrayList<Skill> skillsToTeach = getArguments().getParcelableArrayList(KEY_SKILLS_TO_TEACH);
        mainRecyclerAdapter.addSkills(skillsToTeach);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_teach, container, false);;
        initializeRecyclerView(layout);


        addTeachButton = layout.findViewById(R.id.add_teach_button);
        addTeachButton.setOnClickListener(view -> teachFragmentInterface.showAddSkillFragment());
        return layout;
    }

    private void initializeRecyclerView(View layout) {
        recyclerView = layout.findViewById(R.id.teach_recycler_view);
        recyclerView.setAdapter(mainRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new CustomDivider(15));
        recyclerView.setHasFixedSize(true);

    }


    public interface TeachFragmentInterface {
        void showAddSkillFragment();
    }
}
