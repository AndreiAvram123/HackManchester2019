package com.example.hive.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.hive.R;
import com.example.hive.model.CustomUser;
import com.example.hive.model.Skill;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddSkillFragment extends Fragment {

   private ImageView backButton;
   private Spinner difficultySpinner;
   private Button finishAddSkillButton;
   private EditText titleInputField;
   private EditText descriptionInputField;

   private AddSkillFragmentInterface addSkillFragmentInterface;

    public AddSkillFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_add_skill, container, false);
        titleInputField = layout.findViewById(R.id.title_input_field_value);
        descriptionInputField = layout.findViewById(R.id.description_input_field_value);

        addSkillFragmentInterface = (AddSkillFragmentInterface) getActivity();
        backButton = layout.findViewById(R.id.backButtonAddSkillFragment);
        backButton.setOnClickListener(view -> getFragmentManager().popBackStack());


        finishAddSkillButton = layout.findViewById(R.id.finish_add_skill_button);
        finishAddSkillButton.setOnClickListener(view ->{
            addSkillFragmentInterface.addSkillToTeachFragment(createInterestFromFields());

        });
        difficultySpinner = layout.findViewById(R.id.difficulty_spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.difficulty_levels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);
        return layout;
    }

    private Skill createInterestFromFields() {
        String difficulty = difficultySpinner.getSelectedItem().toString();
        String title = titleInputField.getText().toString();
        String description = descriptionInputField.getText().toString();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        CustomUser customUser = new CustomUser(currentUser.getDisplayName(),currentUser.getEmail(),currentUser.getPhotoUrl().toString());
        return new Skill(title,difficulty,description,customUser);
    }

    public interface AddSkillFragmentInterface{
        void addSkillToTeachFragment(Skill skill);
    }

}
