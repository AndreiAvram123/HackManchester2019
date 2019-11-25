package com.example.hive.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.hive.R;

public class AdapterTeach extends MainRecyclerAdapter  {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_teach, parent, false);
        mainRecyclerAdapterInterface = (MainRecyclerAdapterInterface) parent.getContext();
        return new AdapterTeach.ViewHolder(view);
    }
}
