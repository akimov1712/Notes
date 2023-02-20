package com.example.notes;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.ViewModel.MainViewModel;
import com.example.notes.models.NoteModel;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editDescr;
    private TextView tvDate;
    private Button choiceDate;
    private Button changeDate;
    Calendar dateAndTime = Calendar.getInstance();

    private String title;
    private String descr;
    private String arendToDay;

    MainViewModel viewModel;

    String tvDateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTitle = findViewById(R.id.edTitle);
        editDescr = findViewById(R.id.edDescr);
        tvDate = findViewById(R.id.tvDate);
        changeDate = findViewById(R.id.changeDate);
        choiceDate = findViewById(R.id.choiceDate);

        tvDateText = (String) tvDate.getText();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        if (tvDateText.isEmpty()){
            choiceDate.setVisibility(View.VISIBLE);
        }

    }

    public void onClick(View view) {
        new DatePickerDialog(AddActivity.this, R.style.my_dialog_theme,d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void setInitialDateTime() {

        tvDate.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
            changeDate.setVisibility(View.VISIBLE);
            tvDate.setVisibility(View.VISIBLE);
            choiceDate.setVisibility(View.INVISIBLE);
        }
    };

    public void onClickSendData(View view) {

        title = editTitle.getText().toString().trim();
        descr = editDescr.getText().toString().trim();
        arendToDay = (String) tvDate.getText();

        if (isFilled(title, descr, arendToDay)) {
            NoteModel note = new NoteModel(title, descr, arendToDay);
            viewModel.insertNote(note);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(this, "Одно из полей не заполнено", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isFilled(String title, String description, String arendToDay){
        return !title.isEmpty() && !descr.isEmpty() && !arendToDay.isEmpty();
    }

}