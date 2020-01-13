package com.example.sprint.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint.R;
import com.example.sprint.detailSprint.ShareDataInterface;
import com.example.sprint.model.Task;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * Created by auzan on 7/24/2019.
 * Github: @auzanassdq
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.CategoryViewHolder> {

    private final Context context;
    private int sprintId;
    private ArrayList<Task> listTask;

    ShareDataInterface shareDataInterface;

    public TaskAdapter(Context context) {
        this.context = context;
    }

    public int getSprintId() {
        return sprintId;
    }

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
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
    public void onBindViewHolder(@NonNull final CategoryViewHolder holder, final int i) {
        if (getSprintId() == getListTask().get(i).getSprintId()) {
            holder.checkboxTask.setText(getListTask().get(i).getTitle());
            boolean status = getListTask().get(i).getStatus();

            if (status) {
                holder.checkboxTask.setChecked(true);
            } else {
                holder.checkboxTask.setChecked(false);
            }
        } else {
            holder.rootView.setLayoutParams(holder.params);
        }

        holder.checkboxTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = getListTask().get(i);
                boolean status = task.getStatus();

                holder.checkboxTask.setChecked(!status);
                task.setStatus(!status);

                shareDataInterface = (ShareDataInterface) context;
                shareDataInterface.checkTask(task);
                Log.d(TAG, "onClick: " + task.getStatus());
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = getListTask().get(i);
                shareDataInterface = (ShareDataInterface) context;
                shareDataInterface.getTask(task);
            }
        });

        switch (getListTask().get(i).getKesulitanId()){
            case 1 : holder.imgDifficult.setColorFilter(Color.parseColor("#55efc4"));
                break;
            case 2 : holder.imgDifficult.setColorFilter(Color.parseColor("#0984e3"));
                break;
            case 3 : holder.imgDifficult.setColorFilter(Color.parseColor("#fdcb6e"));
                break;
            case 4 : holder.imgDifficult.setColorFilter(Color.parseColor("#ff7675"));
                break;
            default: holder.imgDifficult.setColorFilter(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return getListTask().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkboxTask;
        ImageView btnEdit, imgDifficult;
        CardView rootView;
        CardView.LayoutParams params;

        CategoryViewHolder(View item){
            super(item);
            params = new CardView.LayoutParams(0, 0);
            rootView = item.findViewById(R.id.task_card);
            checkboxTask = item.findViewById(R.id.checkbox_task);
            btnEdit = itemView.findViewById(R.id.img_edit);
            imgDifficult = itemView.findViewById(R.id.img_difficult);
        }
    }
}
