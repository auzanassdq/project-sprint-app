package com.example.sprint.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sprint.R;
import com.example.sprint.adapter.SprintAdapter;
import com.example.sprint.model.Sprint;
import com.example.sprint.model.SprintData;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView rvCategory;
    private ArrayList<Sprint> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        rvCategory = root.findViewById(R.id.rv_category);
        list = new ArrayList<>();
        list.addAll(SprintData.getListData());
        showList();

        return root;
    }

    private void showList() {
        rvCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        SprintAdapter adapter = new SprintAdapter(getContext());
        adapter.setListSprint(list);
        rvCategory.setAdapter(adapter);
    }
}