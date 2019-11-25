package com.example.hive.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.hive.R;
import com.example.hive.model.Skill;
import com.squareup.picasso.Picasso;

public class AdapterLearn extends MainRecyclerAdapter {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_learn, parent, false);
        mainRecyclerAdapterInterface = (MainRecyclerAdapterInterface) parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        ViewHolder viewHolder = (ViewHolder) holder;
        Skill currentSkill = skills.get(position);

        viewHolder.username.setText(currentSkill.getUser().getUsername());
        Picasso.get().load(currentSkill.getUser().getProfilePictureUri())
                .into(viewHolder.userProfilePicture);
        holder.layout.setOnClickListener(view -> mainRecyclerAdapterInterface.extendSkill(currentSkill));
    }

    class ViewHolder extends MainRecyclerAdapter.ViewHolder {
        TextView username;
        ImageView userProfilePicture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username_item_list);
           userProfilePicture = itemView.findViewById(R.id.user_picture_list);
        }
    }
}
