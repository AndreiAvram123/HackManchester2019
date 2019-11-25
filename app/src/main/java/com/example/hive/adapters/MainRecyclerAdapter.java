package com.example.hive.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.model.Skill;

import java.util.ArrayList;
import java.util.List;

public abstract class MainRecyclerAdapter extends  RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>  {
    protected ArrayList<Skill> skills = new ArrayList<>();
    protected MainRecyclerAdapterInterface mainRecyclerAdapterInterface;

    public void addSkill(Skill skill){
        skills.add(skill);
        notifyDataSetChanged();
    }
    public void addSkills(List<Skill> skillList){
        skills.addAll(skillList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText( skills.get(position).getSkillTitle());
        holder.difficulty.setText(skills.get(position).getSkillDifficulty());

    }


    @Override
    public int getItemCount() {
        return skills.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
      TextView difficulty;
      TextView title;

      View layout;

        public ViewHolder(@NonNull View itemView) {
             super(itemView);
             layout = itemView;
             difficulty = itemView.findViewById(R.id.difficulty_value_list);
             title = itemView.findViewById(R.id.skill_title_list);

        }
    }
    public interface MainRecyclerAdapterInterface{
        void extendSkill(Skill skill);
    }
}
