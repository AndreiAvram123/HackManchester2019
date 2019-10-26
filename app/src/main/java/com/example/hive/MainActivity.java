package com.example.hive;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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
