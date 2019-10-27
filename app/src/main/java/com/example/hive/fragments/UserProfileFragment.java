package com.example.hive.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.adapters.UserProfileAdapter;
import com.example.hive.model.CustomDivider;
import com.example.hive.model.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserProfileAdapter userProfileAdapter;
    private ImageView closeButton;
    private static final String KEY_USER = "KEY_USER";
    private User user;
    private TextView userName;

   public static UserProfileFragment newInstance(@NonNull User user){
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER,user);
        UserProfileFragment userProfileFragment = new UserProfileFragment();
        userProfileFragment.setArguments(bundle);
       return userProfileFragment;
   }
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_user_profile,container,false);
        user = getArguments().getParcelable(KEY_USER);
        userName = layout.findViewById(R.id.profile_name);
        userName.setText(user.getUsername());


        closeButton = layout.findViewById(R.id.close_button_profile);
        closeButton.setOnClickListener(view -> getFragmentManager().popBackStack());
        initializeRecyclerView(layout);


        return layout;
    }

    private void initializeRecyclerView(View layout) {

        recyclerView = layout.findViewById(R.id.recycler_view_profile);
        if(user.getSkillsToTeach() ==null){
            userProfileAdapter = new UserProfileAdapter(new ArrayList<>(),getActivity());
        }else{
            userProfileAdapter = new UserProfileAdapter(user.getSkillsToTeach(),getActivity());
        }
        recyclerView.setAdapter(userProfileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new CustomDivider(15));
        recyclerView.setHasFixedSize(true);
    }

}
