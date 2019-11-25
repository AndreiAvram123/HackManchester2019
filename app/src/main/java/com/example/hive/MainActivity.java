package com.example.hive;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.example.hive.adapters.MainRecyclerAdapter;
import com.example.hive.adapters.UserProfileAdapter;
import com.example.hive.fragments.AddSkillFragment;
import com.example.hive.fragments.ExtendedLearnInterest;
import com.example.hive.fragments.MainFragment;
import com.example.hive.fragments.MapsFragment;
import com.example.hive.fragments.MyProfileFragment;
import com.example.hive.fragments.TeachFragment;
import com.example.hive.fragments.UserProfileFragment;
import com.example.hive.model.Skill;
import com.example.hive.model.User;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TeachFragment.TeachFragmentInterface,
        AddSkillFragment.AddSkillFragmentInterface, MainRecyclerAdapter.MainRecyclerAdapterInterface,
        MapsFragment.MapsFragmentInterface, MyProfileFragment.MyProfilePictureInterface,
        UserProfileAdapter.UserProfileAdapterInterface {

    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private static final int CODE_FINE_LOCATION = 2;
    private static final int CODE_COURSE_LOCATION = 3;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        getSkillsToTeach();

        mainFragment = MainFragment.newInstance(users);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_main,mainFragment)
                .addToBackStack(null)
                .commit();


        getPermissions();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void getSkillsToTeach() {

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                users.add(user);
                if(user.getEmail().toLowerCase().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail().toLowerCase())) {
                   if(user.getSkillsToTeach()!=null) {
                       for (Skill skill : user.getSkillsToTeach()) {
                           mainFragment.addInterestToTeachFragment(skill);

                       }
                       if(user.getInterests()!=null) {
                           for (Skill skill : user.getInterests()) {
                               mainFragment.addInterestToLearnFragment(skill);
                           }
                       }
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

    private void getPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //request fine location permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    CODE_FINE_LOCATION);

        }else{
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                //request fine location permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        CODE_COURSE_LOCATION);

            }
        }
    }


    @Override
    public void showAddSkillFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.container_main,new AddSkillFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void addSkillToTeachFragment(Skill skill) {
       mainFragment.addInterestToTeachFragment(skill);
       addTeachSkillToDatabase(skill);
       getSupportFragmentManager().popBackStack();
    }

    private void addTeachSkillToDatabase(Skill skill){
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                user.addSkillToTeach(skill);
                if(user.getEmail().toLowerCase().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail().toLowerCase()))
                {
                  dataSnapshot.getRef().child("skillsToTeach").setValue(user.getSkillsToTeach());
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

    @Override
    public void extendSkill(Skill skill) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, ExtendedLearnInterest.newInstance(skill))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openUserProfile(LatLng location) {
        User user = getUserProfile(location);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, UserProfileFragment.newInstance(user))
                .addToBackStack(null)
                .commit();
    }

    private User getUserProfile(LatLng location) {
        for(User user :users){
            if(user.getLatitude() == location.latitude && user.getLongitute() == location.longitude){
                return user;
            }
        }
        return null;
    }

    @Override
    public void showMainFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.container_main,mainFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void signOutCurrentUser() {
        firebaseAuth.signOut();
        startActivity(new Intent(this,StartScreenActivity.class));
        finish();
    }

    @Override
    public void addSkillToCurrentUser(Skill skill) {
        getSupportFragmentManager().popBackStack();
        mainFragment.addInterestToLearnFragment(skill);
        addInterestToDatabase(skill);
    }

    private void addInterestToDatabase(Skill skill) {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                User user = dataSnapshot.getValue(User.class);
                user.addInterest(skill);
                if(user.getEmail().toLowerCase().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail().toLowerCase()))
                {
                    dataSnapshot.getRef().child("interests").setValue(user.getSkillsToTeach());
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
}
