package com.example.hive;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.example.hive.adapters.MainRecyclerAdapter;
import com.example.hive.fragments.AddSkillFragment;
import com.example.hive.fragments.ExtendedSkillFragment;
import com.example.hive.fragments.MainFragment;
import com.example.hive.fragments.MapsFragment;
import com.example.hive.fragments.MyProfileFragment;
import com.example.hive.fragments.TeachFragment;
import com.example.hive.fragments.UserProfileFragment;
import com.example.hive.model.Skill;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements TeachFragment.TeachFragmentInterface,
        AddSkillFragment.AddSkillFragmentInterface, MainRecyclerAdapter.MainRecyclerAdapterInterface,
        MapsFragment.MapsFragmentInterface, MyProfileFragment.MyProfilePictureInterface {

    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private static final int CODE_FINE_LOCATION = 2;
    private static final int CODE_COURSE_LOCATION = 3;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = MainFragment.newInstance();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_main,mainFragment)
                .addToBackStack(null)
                .commit();


        getPermissions();
        firebaseAuth = FirebaseAuth.getInstance();
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
       mainFragment.addSkillToTeachFragment(skill);
       getSupportFragmentManager().popBackStack();
    }

    @Override
    public void extendSkill(Skill skill) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main,ExtendedSkillFragment.newInstance(skill))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openUserProfile() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, UserProfileFragment.newInstance())
                .addToBackStack(null)
                .commit();
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
}
