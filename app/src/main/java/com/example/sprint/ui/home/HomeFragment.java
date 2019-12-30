package com.example.sprint.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint.R;
import com.example.sprint.adapter.SprintAdapter;
import com.example.sprint.model.Sprint;
import com.example.sprint.model.SprintList;
import com.example.sprint.network.GetDataService;
import com.example.sprint.network.RetrofitClientInstance;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    final public static String KEY_SPRINTS = "key_sprints";
    private ProgressBar progressBar;

    private RecyclerView rvCategory;
    private ArrayList<Sprint> sprints = new ArrayList<>();
    private SprintAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new SprintAdapter(getContext());

        initViews(view);
        showLoading(true);

        if (savedInstanceState == null) {
            loadJSON();
        } else {
            sprints = savedInstanceState.getParcelableArrayList(KEY_SPRINTS);
            generateSprintList();
        }
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.progress_bar);
        rvCategory = view.findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void loadJSON() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<SprintList> call = service.getAllSprint();
        call.enqueue(new Callback<SprintList>() {
            @Override
            public void onResponse(Call<SprintList> call, Response<SprintList> response) {
                if (response.body() != null) {
                    sprints = response.body().getResults();
                    generateSprintList();
                    showLoading(false);
                } else {
                    Toast.makeText(getContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SprintList> call, Throwable t) {
                Toast.makeText(getContext(), "Tidak dapat memuat data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateSprintList() {
        adapter.setListSprint(sprints);
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