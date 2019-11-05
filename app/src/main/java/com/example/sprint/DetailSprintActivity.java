package com.example.sprint;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint.adapter.TaskAdapter;
import com.example.sprint.model.Task;
import com.example.sprint.model.TaskList;
import com.example.sprint.network.GetDataService;
import com.example.sprint.network.RetrofitClientInstance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailSprintActivity extends AppCompatActivity {

    final public static String KEY_SPRINTS = "key_sprints";

    private RecyclerView rvCategory;
    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskAdapter adapter;
    private Context context;

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

        context = this;
        adapter = new TaskAdapter(this);

        initViews();
        if (savedInstanceState == null) {
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
        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadJSON() {

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Observable<TaskList> observable = service.getAllTask();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<TaskList>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TaskList taskList) {
                tasks = taskList.getResults();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context, "Data tidak dapat dimuat", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                generateSprintList();
            }
        });
    }

    private void generateSprintList() {
        adapter.setListTask(tasks);
        rvCategory.setAdapter(adapter);
    }

}
