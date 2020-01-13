package com.example.sprint.detailSprint;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sprint.R;
import com.example.sprint.model.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Created by auzan on 1/13/2020.
 * Github: @auzanassdq
 */
public class TaskBottomSheetDialog extends BottomSheetDialogFragment {
    ShareDataInterface shareDataInterface;

    private Button btnSubmit;
    private TextInputEditText edtTask;
    private Spinner spinner;

    private Task task;
    private Bundle mArgs;

    private int sprintId;
    private String difficult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.bsd_new_task, null, false);

        mArgs = getArguments();
        if (mArgs != null) {
            sprintId = mArgs.getInt("sprint_id");
        }

        btnSubmit =  view.findViewById(R.id.btn_save);
        edtTask = view.findViewById(R.id.edt_task);
        spinner = view.findViewById(R.id.spinner_difficulty);

        task = new Task();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                difficult = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taskName = edtTask.getText().toString().trim();
                task.setTitle(taskName);
                task.setSprintId(sprintId);
                task.setStatus(false);
                task.setKesulitanId(getDifficultyId());
                Log.d("TASK", "onClick: " + task.getTitle() + task.getSprintId() + task.getStatus() + task.getKesulitanId());

                shareDataInterface.postData(task);
            }
        });

        return view;
    }

    public int getDifficultyId(){
        switch (difficult) {
            case "Medium" : return 2;
            case "Hard" : return 3;
            case "Very Hard" : return 4;
            default: return 1;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            shareDataInterface = (ShareDataInterface) context;
        } catch (RuntimeException e) {
            throw new RuntimeException(context.toString()+"Must Implement method");
        }
    }
}
