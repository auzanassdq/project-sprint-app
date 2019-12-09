package com.example.sprint.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint.DetailSprintActivity;
import com.example.sprint.R;
import com.example.sprint.model.Sprint;

import java.util.ArrayList;


/**
 * Created by auzan on 7/24/2019.
 * Github: @auzanassdq
 */
public class SprintAdapter extends RecyclerView.Adapter<SprintAdapter.CategoryViewHolder> {

    private final Context context;
    private ArrayList<Sprint> listSprint;

    public SprintAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Sprint> getListSprint() {
        return listSprint;
    }

    public void setListSprint(ArrayList<Sprint> listSprint) {
        this.listSprint = listSprint;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sprint, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int i) {
        holder.tvTitle.setText(getListSprint().get(i).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sprint sprint = getListSprint().get(i);
                Intent intent = new Intent(context, DetailSprintActivity.class);
                intent.putExtra(DetailSprintActivity.EXTRA_MOVIE, sprint);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListSprint().size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;

        CategoryViewHolder(View item){
            super(item);
            tvTitle = item.findViewById(R.id.tv_title);
        }
    }
}
