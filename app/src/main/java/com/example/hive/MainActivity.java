package com.example.hive;

import android.Manifest;
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
import com.example.hive.fragments.TeachFragment;
import com.example.hive.model.Skill;

public class MainActivity extends AppCompatActivity implements TeachFragment.TeachFragmentInterface,
        AddSkillFragment.AddSkillFragmentInterface, MainRecyclerAdapter.MainRecyclerAdapterInterface {

    private FragmentManager fragmentManager;
    private MainFragment mainFragment;
    private static final int CODE_FINE_LOCATION = 2;
    private static final int CODE_COURSE_LOCATION = 3;
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
}
