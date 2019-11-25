package com.example.hive.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.hive.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends Fragment {

    private ImageView closeButton;
    private MyProfilePictureInterface myProfilePictureInterface;
    private FirebaseUser firebaseUser;
    private ImageView profilePicture;
    private TextView email;
    private TextView userName;

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
        View layout = inflater.inflate(R.layout.fragment_my_profile, container, false);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        profilePicture = layout.findViewById(R.id.profile_picture_my_profile);
        Picasso.get().load(firebaseUser.getPhotoUrl())
                .into(profilePicture);

        userName = layout.findViewById(R.id.my_profile_name);
        userName.setText(firebaseUser.getDisplayName());


        email = layout.findViewById(R.id.email_my_profile);
        email.setText(firebaseUser.getEmail());



        Button signOutButton = layout.findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(view -> myProfilePictureInterface.signOutCurrentUser());


        myProfilePictureInterface = (MyProfilePictureInterface) getActivity();
        closeButton = layout.findViewById(R.id.close_button_my_profile);
        closeButton.setOnClickListener(view -> getFragmentManager().popBackStack());



        return layout;

    }


    public interface MyProfilePictureInterface{
        void signOutCurrentUser();
    }
}
