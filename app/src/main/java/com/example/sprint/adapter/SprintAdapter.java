package com.example.sprint.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint.detailSprint.DetailSprintActivity;
import com.example.sprint.R;
import com.example.sprint.model.Sprint;
import com.example.sprint.model.Task;

import java.util.ArrayList;


/**
 * Created by auzan on 7/24/2019.
 * Github: @auzanassdq
 */
public class SprintAdapter extends RecyclerView.Adapter<SprintAdapter.CategoryViewHolder> {

    private final Context context;
    private int sprintId;
    private ArrayList<Task> listTask;
    private ArrayList<Sprint> listSprint;

    int jlhTask = 0;

    public SprintAdapter(Context context) {
        this.context = context;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public ArrayList<Sprint> getListSprint() {
        return listSprint;
    }

    public void setListSprint(ArrayList<Sprint> listSprint) {
        this.listSprint = listSprint;
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
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sprint, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int i) {
        setSprintId(getListSprint().get(i).getId());

        holder.tvTitle.setText(getListSprint().get(i).getTitle());
        holder.progressBar.setProgress(getPersentase(getSprintId()));
        holder.tvPersentase.setText(getPersentase(getSprintId()) + "% Selesai");
        holder.tvTotalTask.setText(jlhTask + " Task");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sprint sprint = getListSprint().get(i);
                Intent intent = new Intent(context, DetailSprintActivity.class);
                intent.putExtra(DetailSprintActivity.EXTRA_SPRINT, sprint);
                context.startActivity(intent);
            }
        });
    }

    public int getPersentase(int sprintId){
        double persen;
        int selesai = 0;
        jlhTask = 0;
        for ( Task item : getListTask()) {
            if (item.getSprintId() == sprintId){
                jlhTask += 1;
                if (item.getStatus()){
                    selesai += 1;
                }
            }
        }

        if (jlhTask != 0){
            persen = 100/jlhTask;
            persen = persen * selesai;
        } else {
            persen = 0;
        }

        Log.d("TASK", "getPersentase: " + selesai + jlhTask + persen );
        return (int)persen;
    }

    @Override
    public int getItemCount() {
        return getListSprint().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvPersentase, tvTotalTask;
        ProgressBar progressBar;

        CategoryViewHolder(View item){
            super(item);
            tvTitle = item.findViewById(R.id.tv_title);
            tvPersentase = item.findViewById(R.id.tv_persentase);
            tvTotalTask = item.findViewById(R.id.tv_total_task);
            progressBar = item.findViewById(R.id.simpleProgressBar);
        }
    }


}
