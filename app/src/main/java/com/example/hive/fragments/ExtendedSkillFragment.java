package com.example.hive.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.hive.R;
import com.example.hive.model.Skill;


public class ExtendedSkillFragment extends Fragment {
   private final static String SKILL_TAG = "SKILL_TAG";
   private Skill skill;
   private TextView description;
   private TextView title;
   private TextView difficulty;
   private ImageView backButton;

    public ExtendedSkillFragment() {
        // Required empty public constructor
    }

   public static ExtendedSkillFragment newInstance(Skill skill){
        Bundle bundle = new Bundle();
        bundle.putParcelable(SKILL_TAG,skill);
        ExtendedSkillFragment extendedSkillFragment = new ExtendedSkillFragment();
        extendedSkillFragment.setArguments(bundle);
        return extendedSkillFragment;
   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_extended_skill, container, false);
        skill = getArguments().getParcelable(SKILL_TAG);
        initializeUI(layout);
        return layout;
    }

    private void initializeUI(View layout) {
        findViews(layout);
         populateWithData();

    }

    private void populateWithData() {
        difficulty.setText(skill.getSkillDifficulty());
        description.setText(skill.getSkillDescription());
        title.setText(skill.getSkillTitle());
    }

    private void findViews(View layout) {
        difficulty = layout.findViewById(R.id.extended_skill_difficulty);
        title = layout.findViewById(R.id.extended_skill_title);
        description = layout.findViewById(R.id.extended_skill_description);
        backButton = layout.findViewById(R.id.extended_skill_back_button);
        backButton.setOnClickListener(view -> getFragmentManager().popBackStack());
    }

}
