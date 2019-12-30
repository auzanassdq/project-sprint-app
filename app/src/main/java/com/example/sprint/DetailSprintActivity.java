package com.example.sprint;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint.adapter.TaskAdapter;
import com.example.sprint.model.Sprint;
import com.example.sprint.model.Task;
import com.example.sprint.model.TaskList;
import com.example.sprint.network.GetDataService;
import com.example.sprint.network.RetrofitClientInstance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSprintActivity extends AppCompatActivity {

    final public static String KEY_SPRINTS = "key_sprints";
    final public static String EXTRA_MOVIE = "extra_movie";

    private RecyclerView rvCategory;
    private ProgressBar progressBar;
    private Toolbar toolbar;

    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskAdapter adapter;
    private Context context;
    private Sprint sprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sprint);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        context = this;
        adapter = new TaskAdapter(this);

        initViews();
        showLoading(true);

        if (savedInstanceState == null) {
            sprint = getIntent().getParcelableExtra(EXTRA_MOVIE);
            loadJSON();
        } else {
            tasks = savedInstanceState.getParcelableArrayList(KEY_SPRINTS);
            generateSprintList();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initViews() {
        progressBar = findViewById(R.id.progress_bar);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadJSON() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<TaskList> call = service.getAllTask();
        call.enqueue(new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                if (response.body() != null) {
                    tasks = response.body().getResults();
                    generateSprintList();
                    showLoading(false);
                } else {
                    Toast.makeText(DetailSprintActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TaskList> call, Throwable t) {
                Toast.makeText(DetailSprintActivity.this, "Tidak dapat memuat data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateSprintList() {
        adapter.setListTask(tasks);
        adapter.setSprintId(sprint.getId());
        rvCategory.setAdapter(adapter);
    }

    private void showLoading(boolean state) {
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}
