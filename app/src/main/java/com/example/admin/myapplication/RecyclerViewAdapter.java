package com.example.admin.myapplication;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bolts.Task;

class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Task> tasks;
    protected  Context context;
    public RecycleViewAdapter(Context context, List<Task>tasks){
        this.tasks = tasks;
        this.context =context;

    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerViewHolders viewHolders = null;
        View layoutView =LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list,parent,false);
        viewHolders =new RecyclerViewHolders(layoutView,tasks);
        return viewHolders;

    }


    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.categoryTitle.setText((Integer) tasks.get(position).getResult());

    }

    @Override
    public int getItemCount() {
        return this.tasks.size();
    }
}




