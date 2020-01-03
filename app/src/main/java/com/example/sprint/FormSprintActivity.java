package com.example.sprint;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sprint.model.Sprint;
import com.example.sprint.network.GetDataService;
import com.example.sprint.network.RetrofitClientInstance;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormSprintActivity extends AppCompatActivity {

    private Sprint sprint;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResultStart, tvDateResultEnd;
    private Button btDatePickerStart, btDatePickerEnd, btnSave;
    private TextInputEditText edtTitle, edtDescription;

    String title, desc, dateStart, dateEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sprint);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sprint = new Sprint();

        edtTitle = findViewById(R.id.title_edit_text);
        edtDescription = findViewById(R.id.description_edit_text);

//      Format tanggal
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

//      Start
        tvDateResultStart = findViewById(R.id.tv_dateresult_start);
        btDatePickerStart = findViewById(R.id.bt_datepicker_start);
        btDatePickerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog("Start", tvDateResultStart);
            }
        });

//      End
        tvDateResultEnd = findViewById(R.id.tv_dateresult_end);
        btDatePickerEnd = findViewById(R.id.bt_datepicker_end);
        btDatePickerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog("End", tvDateResultEnd);
            }
        });

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = edtTitle.getText().toString().trim();
                desc = edtDescription.getText().toString().trim();
                sprint.setTitle(title);
                sprint.setDesc(desc);
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(dateStart) && !TextUtils.isEmpty(dateEnd)){
                    submit();
                }
            }
        });
    }

    private void submit() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Sprint> call = service.postSprint(sprint);
        call.enqueue(new Callback<Sprint>() {
            @Override
            public void onResponse(Call<Sprint> call, Response<Sprint> response) {

                if (response.isSuccessful()) {
                    Log.d("POST", "onResponse: " + response.body().toString() );
                    Toast.makeText(FormSprintActivity.this, "Yeaay berhasil", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormSprintActivity.this, "Sprint gagal dibuat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Sprint> call, Throwable t) {
                Log.d("POST", "onFailure: " + "Something error");
            }
        });
    }

    private void showDateDialog(final String status, final TextView tvStatus){

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                if (status.equals("Start")) {
                    dateStart = dateFormatter.format(newDate.getTime());
                    sprint.setDateStart(dateStart);
                } else if (status.equals("End")) {
                    dateEnd = dateFormatter.format(newDate.getTime());
                    sprint.setDateEnd(dateEnd);
                }

                tvStatus.setText(String.format("%s : %s", status, dateFormatter.format(newDate.getTime())));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
