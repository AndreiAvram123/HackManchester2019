package com.example.hive.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.adapters.MyProfileAdapter;
import com.example.hive.model.CustomDivider;
import com.example.hive.model.Skill;
import com.example.hive.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

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
    private FirebaseUser firebaseUser;
    private ImageView profilePicture;
    private TextView email;
    private TextView userName;
    private DatabaseReference databaseReference;

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
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        profilePicture = layout.findViewById(R.id.profile_picture_my_profile);
        Picasso.get().load(firebaseUser.getPhotoUrl())
                .into(profilePicture);

        userName = layout.findViewById(R.id.my_profile_name);
        userName.setText(firebaseUser.getDisplayName());


        email = layout.findViewById(R.id.email_my_profile);
        email.setText(firebaseUser.getEmail());


        addAbilityButton = layout.findViewById(R.id.add_skill_my_profile);
        addAbilityButton.setOnClickListener(view -> myProfileAdapter.addAbility(new Skill(
                myProfileSpinner.getSelectedItem().toString(),"",""))
        );

        Button signOutButton = layout.findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(view -> myProfilePictureInterface.signOutCurrentUser());


         initializeRecyclerView(layout);
        myProfilePictureInterface = (MyProfilePictureInterface) getActivity();
        closeButton = layout.findViewById(R.id.close_button_my_profile);
        closeButton.setOnClickListener(view -> myProfilePictureInterface.showMainFragment());

        myProfileSpinner = layout.findViewById(R.id.spinner_my_profile);


        //todo
        //good to remember
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.interests, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myProfileSpinner.setAdapter(adapter);

        getAbilities();

        return layout;

    }

    private void getAbilities() {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                if(user.getEmail().toLowerCase()
                        .equals(firebaseUser.getEmail().toLowerCase())){
                    Log.d("test",user + "");
                    for(Skill skill : user.getInterests()) {
                        myProfileAdapter.addAbility(skill);
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initializeRecyclerView(View layout) {
        ArrayList<Skill> skills =new ArrayList<>();
        recyclerView = layout.findViewById(R.id.my_profile_recycler_view);
        myProfileAdapter = new MyProfileAdapter(skills);
        recyclerView.setAdapter(myProfileAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new CustomDivider(15));
        recyclerView.setHasFixedSize(true);
    }

    public interface MyProfilePictureInterface{
        void showMainFragment();
        void signOutCurrentUser();
    }
}
