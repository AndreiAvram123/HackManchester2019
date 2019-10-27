package com.example.hive.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.model.Skill;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.ViewHolder> {
    private ArrayList<Skill> skills ;
    private UserProfileAdapterInterface userProfileAdapterInterface;

    public UserProfileAdapter(ArrayList<Skill> skills, Activity activity){
        this.skills = skills;
        userProfileAdapterInterface = (UserProfileAdapterInterface) activity;
    }

    public void addSkill(Skill skill){
        skills.add(skill);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_profile,parent,false);

        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.title.setText(skills.get(position).getSkillTitle());
     holder.difficulty.setText(skills.get(position).getSkillDifficulty());
     holder.addSkillButton.setOnClickListener(view -> userProfileAdapterInterface.addSkillToCurrentUser(skills.get(position)));
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
       TextView title;
       TextView difficulty;
       FloatingActionButton addSkillButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title  = itemView.findViewById(R.id.skill_title_user_profile);
            difficulty = itemView.findViewById(R.id.difficulty_value_user_profile);
            addSkillButton = itemView.findViewById(R.id.add_skill_user_profile);
        }
    }

    public interface UserProfileAdapterInterface{
        void addSkillToCurrentUser(Skill skill);
    }
}
