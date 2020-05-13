package com.example.wafil.Wafil.chilyoHouze.Functions;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OpenCalendarDialog {

    private onDateSet ondateset;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private Context context;

    public OpenCalendarDialog(Context context) {
        this.context = context;
    }

    public void setDateInterface(onDateSet listener){
        this.ondateset = listener;
    }

    public void addCalendarListener(){
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                ondateset.onSuccess(sdf.format(myCalendar.getTime()));
            }
        };
    }

    public void openCalendar(){
        myCalendar = Calendar.getInstance();
        new DatePickerDialog(context, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void openCalendarWithView(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendar();
            }
        });
    }

    public interface onDateSet{
        void onSuccess(String date);
    }
}
