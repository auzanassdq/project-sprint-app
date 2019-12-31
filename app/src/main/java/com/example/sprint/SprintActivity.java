package com.example.sprint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sprint.adapter.SprintAdapter;
import com.example.sprint.model.Sprint;
import com.example.sprint.model.SprintList;
import com.example.sprint.model.Task;
import com.example.sprint.model.TaskList;
import com.example.sprint.network.GetDataService;
import com.example.sprint.network.RetrofitClientInstance;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SprintActivity extends AppCompatActivity {

    final private static String KEY_SPRINTS = "key_sprints";
    private ProgressBar progressBar;
    private RecyclerView rvCategory;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;

    private ArrayList<Sprint> sprints = new ArrayList<>();
    private ArrayList<Task> tasks = new ArrayList<>();

    private SprintAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprint);

        adapter = new SprintAdapter(this);

        initViews();
        setUpBottomAppBar();
        showLoading(true);

        if (savedInstanceState == null) {
            loadJSON();
        } else {
            sprints = savedInstanceState.getParcelableArrayList(KEY_SPRINTS);
            generateSprintList();
        }

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SprintActivity.this, FormSprintActivity.class);
                startActivity(intent);
//                Toast.makeText(SprintActivity.this, "FAB Clicked.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
            }
        });

        progressBar = findViewById(R.id.progress_bar);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadJSON() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<SprintList> call = service.getAllSprint();
        call.enqueue(new Callback<SprintList>() {
            @Override
            public void onResponse(Call<SprintList> call, Response<SprintList> response) {
                if (response.body() != null) {
                    sprints = response.body().getResults();
                    loadTask();
                } else {
                    Toast.makeText(SprintActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SprintList> call, Throwable t) {
                Toast.makeText(SprintActivity.this, "Tidak dapat memuat data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTask(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        service.getAllTask().enqueue(new Callback<TaskList>() {
            @Override
            public void onResponse(Call<TaskList> call, Response<TaskList> response) {
                if (response.body() != null) {
                    tasks = response.body().getResults();
                    generateSprintList();
                    showLoading(false);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Toast.makeText(SprintActivity.this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TaskList> call, Throwable t) {
                Toast.makeText(SprintActivity.this, "Tidak dapat memuat data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateSprintList() {
        adapter.setListSprint(sprints);
        adapter.setListTask(tasks);
        rvCategory.setAdapter(adapter);
    }

    private void showLoading(boolean state) {
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setUpBottomAppBar() {
        //find id
        bottomAppBar = findViewById(R.id.bar);

        //set bottom bar to Action bar as it is similar like Toolbar
        setSupportActionBar(bottomAppBar);

    }
}
