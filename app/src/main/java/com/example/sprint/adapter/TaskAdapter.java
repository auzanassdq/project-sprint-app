package com.example.sprint.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sprint.R;
import com.example.sprint.model.Task;

import java.util.ArrayList;


/**
 * Created by auzan on 7/24/2019.
 * Github: @auzanassdq
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.CategoryViewHolder> {

    private final Context context;
    private ArrayList<Task> listTask;

    public TaskAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Task> getListTask() {
        return listTask;
    }

    public void setListTask(ArrayList<Task> listTask) {
        this.listTask = listTask;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int i) {
        holder.tvTitle.setText(getListTask().get(i).getTitle());
        holder.tvLevel.setText(getListTask().get(i).getLevel());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Task Task = getListTask().get(i);
//                Intent intent = new Intent(context, DetailTaskActivity.class);
//                intent.putExtra(DetailActivity.EXTRA_BARBER, barberShop);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return getListTask().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvLevel;

        CategoryViewHolder(View item){
            super(item);
            tvTitle = item.findViewById(R.id.tv_task_title);
            tvLevel = item.findViewById(R.id.tv_task_level);
        }
    }
}
