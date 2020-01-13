package com.example.sprint.detailSprint;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sprint.R;
import com.example.sprint.adapter.TaskAdapter;
import com.example.sprint.model.Sprint;
import com.example.sprint.model.Task;
import com.example.sprint.model.TaskList;
import com.example.sprint.network.GetDataService;
import com.example.sprint.network.RetrofitClientInstance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSprintActivity extends AppCompatActivity implements ShareDataInterface{

    final public static String KEY_SPRINTS = "key_sprints";
    final public static String EXTRA_SPRINT = "extra_movie";

    private RecyclerView rvCategory;
    private ProgressBar progressBar;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskAdapter adapter;
    private Context context;
    private Sprint sprint;

    TaskBottomSheetDialog taskBottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sprint);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putInt("sprint_id", sprint.getId());

                taskBottomSheetDialog = new TaskBottomSheetDialog();
                taskBottomSheetDialog.setArguments(args);
                taskBottomSheetDialog.show(getSupportFragmentManager(), "tag");
            }
        });

        context = this;
        adapter = new TaskAdapter(this);

        initViews();
        showLoading(true);

        if (savedInstanceState == null) {
            sprint = getIntent().getParcelableExtra(EXTRA_SPRINT);
            loadJSON();
        } else {
            tasks = savedInstanceState.getParcelableArrayList(KEY_SPRINTS);
            generateSprintList();
        }

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
                    swipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void postData(Task task) {
        taskBottomSheetDialog.dismiss();
        swipeRefreshLayout.setRefreshing(true);
        createNewTask(task);
    }

    @Override
    public void getTask(Task task) {
        Toast.makeText(context, task.getTitle(), Toast.LENGTH_SHORT).show();
        Bundle args = new Bundle();
        args.putParcelable("task", task);

        taskBottomSheetDialog = new TaskBottomSheetDialog();
        taskBottomSheetDialog.setArguments(args);
        taskBottomSheetDialog.show(getSupportFragmentManager(), "tag");
    }

    @Override
    public void putTask(Task task) {
        taskBottomSheetDialog.dismiss();
        swipeRefreshLayout.setRefreshing(true);
        updateTask(task);
    }

    @Override
    public void checkTask(Task task) {
        updateTask(task);
    }

    @Override
    public void deleteData(Task task) {
        taskBottomSheetDialog.dismiss();
        swipeRefreshLayout.setRefreshing(true);
        deleteTask(task);
    }

    public void createNewTask(Task task){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Task> call = service.postTask(task);
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailSprintActivity.this, "Yeaay berhasil", Toast.LENGTH_SHORT).show();
                    loadJSON();
                } else {
                    Toast.makeText(DetailSprintActivity.this, "Task gagal dibuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.d("POST", "onFailure: " + "Something error");
            }
        });
    }

    public void updateTask(Task task){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Task> call = service.putTask(task, task.getId());
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailSprintActivity.this, "Yeaay berhasil diupdate", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Toast.makeText(DetailSprintActivity.this, "Task gagal diupdate", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.d("POST", "onFailure: " + "Something error");
            }
        });
    }

    // TODO delete masih ada bug
    public void deleteTask(Task task){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Task> call = service.deleteTask(task.getId());
        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailSprintActivity.this, "Yeaay berhasil", Toast.LENGTH_SHORT).show();
                    loadJSON();
                } else {
                    Toast.makeText(DetailSprintActivity.this, "Task gagal dihapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.d("POST", "onFailure: " + "Something error");
            }
        });
    }
}
