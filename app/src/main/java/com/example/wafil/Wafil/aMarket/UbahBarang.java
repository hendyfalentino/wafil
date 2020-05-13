package com.example.wafil.Wafil.aMarket;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

import java.util.Calendar;

public class UbahBarang extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1;
    EditText nama, jumlahbb, jumlahbr, jenis;
    TextView tanggal;
    private DatePickerDialog.OnDateSetListener ttanggal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_barang);

        dbHelper = new DataHelper(this);
        nama =  findViewById(R.id.etub1);
        jumlahbb =  findViewById(R.id.etub2);
        jumlahbr = findViewById(R.id.etub3);
        jenis = findViewById(R.id.etub4);
        tanggal = findViewById(R.id.etub5);
        ton1 = findViewById(R.id.tonu1);


        //text view pilih tanggal
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                @SuppressLint("WrongConstant") int month = cal.get(Calendar.MONDAY);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogd = new DatePickerDialog(
                        UbahBarang.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        ttanggal,
                        year, month, day);
                dialogd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogd.show();

            }
        });
        //dialog untuk datepicker
        ttanggal = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d("TB", "Tanggal = " + day + "/" + month + "/" + year);

                String tanggal1 = day + "/" + month + "/" + year;
                tanggal.setText(tanggal1);
            }
        };


        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang WHERE id_barang ='"+
                getIntent().getStringExtra("id_barang")+"'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);

            nama.setText(cursor.getString(2).toString());
            jumlahbb.setText(cursor.getString(3).toString());
            jumlahbr.setText(cursor.getString(4).toString());
            jenis.setText(cursor.getString(5).toString());
            tanggal.setText(cursor.getString(1).toString());


            //tombol simpan
            ton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("update barang set nama_barang='" +
                            nama.getText().toString() + "', jumlah_barang_bagus='" +
                            jumlahbb.getText().toString() + "', jumlah_barang_rusak='" +
                            jumlahbr.getText().toString() + "', tanggal='" +
                            tanggal.getText().toString() + "', jenis_barang='" +
                            jenis.getText().toString() + "' where id_barang='"+
                            getIntent().getStringExtra("id_barang") + "'");
                    Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    HomeAdmin.ha.RefreshList();
                    finish();
                }
            });

        }
    }

}
