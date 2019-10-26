package com.example.hive.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.adapters.MainRecyclerAdapter;
import com.example.hive.model.CustomDivider;
import com.example.hive.model.Skill;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeachFragment extends Fragment {


    private TeachFragmentInterface teachFragmentInterface;
    private FloatingActionButton addTeachButton;
    private RecyclerView recyclerView;
    private MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter();

    public TeachFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TeachFragment newInstance() {
        return new TeachFragment();
    }



    public void addSkillToAdapter(Skill skill){
        mainRecyclerAdapter.addSkill(skill);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Text","yo");
        View layout = inflater.inflate(R.layout.fragment_teach, container, false);
        teachFragmentInterface = (TeachFragmentInterface) getActivity();
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
