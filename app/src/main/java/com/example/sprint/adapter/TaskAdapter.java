package com.example.sprint.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    private int sprintId;
    private ArrayList<Task> listTask;

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
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int i) {
        if (getSprintId() == getListTask().get(i).getSprintId()) {
            holder.tvTitle.setText(getListTask().get(i).getTitle());
            boolean status = getListTask().get(i).getStatus();

            if (status) {
                holder.tvStatus.setText("Selesai");
            } else {
                holder.tvStatus.setText("Belum");
            }
        } else {
            holder.rootView.setLayoutParams(holder.params);
        }


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

    public void getTaskById (int i) {
        if (getSprintId() == getListTask().get(i).getSprintId()) {

        }
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvStatus;
        CardView rootView;
        CardView.LayoutParams params;

        CategoryViewHolder(View item){
            super(item);
            params = new CardView.LayoutParams(0, 0);
            rootView = item.findViewById(R.id.task_card);
            tvTitle = item.findViewById(R.id.tv_task_title);
            tvStatus = item.findViewById(R.id.tv_task_status);
        }
    }
}
