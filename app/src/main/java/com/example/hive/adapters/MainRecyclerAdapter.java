package com.example.hive.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.model.Skill;

import java.util.ArrayList;

public class MainRecyclerAdapter extends  RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>  {

    private ArrayList<Skill> skills = new ArrayList<>();
    private MainRecyclerAdapterInterface mainRecyclerAdapterInterface;


    public void addSkill(Skill skill){
        skills.add(skill);
        Log.d("dfdf",this + "");
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        mainRecyclerAdapterInterface = (MainRecyclerAdapterInterface) parent.getContext();
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText( skills.get(position).getSkillTitle());
        holder.difficulty.setText(skills.get(position).getSkillDifficulty());
        holder.layout.setOnClickListener(view -> mainRecyclerAdapterInterface.extendSkill(skills.get(position)));
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
             difficulty = itemView.findViewById(R.id.difficulty_value);
             title = itemView.findViewById(R.id.skill_title);
        }
    }
    public interface MainRecyclerAdapterInterface{
        void extendSkill(Skill skill);
    }
}
