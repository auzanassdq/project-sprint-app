package com.example.sprint;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormSprintActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResultStart, tvDateResultEnd;
    private Button btDatePickerStart, btDatePickerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sprint);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//      Format tanggal
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

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
    }

    private void showDateDialog(final String status, final TextView tvStatus){

        /*
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /*
          Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /*
                  Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /*
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /*
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvStatus.setText(String.format("%s : %s", status, dateFormatter.format(newDate.getTime())));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /*
         * Tampilkan DatePicker dialog
         */
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
