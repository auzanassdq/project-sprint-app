package com.example.sprint;

import android.os.Bundle;

import com.example.sprint.adapter.SprintAdapter;
import com.example.sprint.adapter.TaskAdapter;
import com.example.sprint.model.Sprint;
import com.example.sprint.model.SprintData;
import com.example.sprint.model.Task;
import com.example.sprint.model.TaskData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class DetailSprintActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<Task> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sprint);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        rvCategory = findViewById(R.id.rv_category);
        list = new ArrayList<>();
        list.addAll(TaskData.getListData());
        Log.d("LIST", "onCreate: " + list);
        showList();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        TaskAdapter adapter = new TaskAdapter(this);
        adapter.setListTask(list);
        rvCategory.setAdapter(adapter);
    }

}
