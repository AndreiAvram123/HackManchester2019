package com.example.hive.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.hive.R;
import com.example.hive.model.Skill;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class MainFragment extends Fragment {

    private ViewPager viewPager;
    private FragmentLearn fragmentLearn;
    private TeachFragment teachFragment;
    private MapsFragment mapsFragment;
    private ImageView profileImage;
    private FirebaseAuth firebaseAuth;
    private TextView userName;
    public MainFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public void addSkillToTeachFragment(Skill skill) {
        teachFragment.addSkillToAdapter(skill);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentLearn = FragmentLearn.newInstance();
        teachFragment = TeachFragment.newInstance();
        mapsFragment = MapsFragment.newInstance();
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        userName = layout.findViewById(R.id.user_name) ;
        userName.setText(firebaseUser.getDisplayName());


        profileImage = layout.findViewById(R.id.profile_picture);
        Picasso.get()
                .load(firebaseUser.getPhotoUrl())
                .into(profileImage);


        profileImage.setOnClickListener(view -> getFragmentManager()
                .beginTransaction().replace(R.id.container_main, MyProfileFragment.newInstance())
                .addToBackStack(null)
                .commit());


        viewPager = layout.findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        return fragmentLearn;
                    case 1: {
                        return teachFragment;
                    }
                    case 2:
                        return mapsFragment;

                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return "Learn";
                }
                if (position == 1) {
                    return "Teach";
                }
                if (position == 2) {
                    return "Map";
                }
                return super.getPageTitle(position);
            }
        });
        return layout;

    }


}
