package com.example.hive.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.adapters.MyProfileAdapter;
import com.example.hive.model.CustomDivider;
import com.example.hive.model.Skill;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

    private ImageView closeButton;
    private RecyclerView recyclerView;
    private MyProfileAdapter myProfileAdapter;
    private Spinner myProfileSpinner;
    private FloatingActionButton addAbilityButton;
   private MyProfilePictureInterface myProfilePictureInterface;


    public MyProfileFragment() {
        // Required empty public constructor
    }

    public static MyProfileFragment newInstance(){
        return new MyProfileFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_my_profile_picture, container, false);
        addAbilityButton = layout.findViewById(R.id.add_skill_my_profile);
        addAbilityButton.setOnClickListener(view -> myProfileAdapter.addAbility(new Skill(
                myProfileSpinner.getSelectedItem().toString(),"","")));

         initializeRecyclerView(layout);
        myProfilePictureInterface = (MyProfilePictureInterface) getActivity();
        closeButton = layout.findViewById(R.id.close_button_my_profile);
        closeButton.setOnClickListener(view -> myProfilePictureInterface.showMainFragment());

        myProfileSpinner = layout.findViewById(R.id.spinner_my_profile);


        //todo
        //good to remember
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.abilities, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myProfileSpinner.setAdapter(adapter);



        return layout;

    }
    private void initializeRecyclerView(View layout) {
        ArrayList<Skill> skills =new ArrayList<>();
        skills.add(new Skill("Learning to sing","Advanced","I am going to learfdfd"));
        skills.add(new Skill("Learning to sing","Advanced","I am going to learfdfd"));
        skills.add(new Skill("Learning to sing","Advanced","I am going to learfdfd"));
        skills.add(new Skill("Learning to sing","Advanced","I am going to learfdfd"));
        recyclerView = layout.findViewById(R.id.my_profile_recycler_view);
        myProfileAdapter = new MyProfileAdapter(skills);
        recyclerView.setAdapter(myProfileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new CustomDivider(15));
        recyclerView.setHasFixedSize(true);
    }

    public interface MyProfilePictureInterface{
        void showMainFragment();
    }
}
