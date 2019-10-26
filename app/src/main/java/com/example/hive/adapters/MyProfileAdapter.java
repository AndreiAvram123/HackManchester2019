package com.example.hive.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hive.R;
import com.example.hive.model.Skill;

import java.util.ArrayList;

public class MyProfileAdapter extends RecyclerView.Adapter<MyProfileAdapter.ViewHolder>{

    private ArrayList<Skill> abilities;

    public MyProfileAdapter(ArrayList<Skill> abilities){
        this.abilities = abilities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_profile,parent,false);
        return new ViewHolder(view);
    }

    public void addAbility(Skill ability){
        abilities.add(ability);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.title.setText(abilities.get(position).getSkillTitle());
      holder.removeItem.setOnClickListener(view-> {
          abilities.remove(position);
          notifyDataSetChanged();
      });
    }

    @Override
    public int getItemCount() {
        return abilities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView removeItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.skill_title);
            removeItem = itemView.findViewById(R.id.remove_skill_my_profile);
        }
    }
}
