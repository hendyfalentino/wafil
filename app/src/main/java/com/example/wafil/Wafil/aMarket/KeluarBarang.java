package com.example.wafil.Wafil.aMarket;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
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

public class KeluarBarang extends AppCompatActivity {
    protected Cursor cursor, cursor1;
    DataHelper dbHelper;
    Button ton1;
    EditText jumlah, jumlah2;
    TextView tanggal, nama, jummm;
    private DatePickerDialog.OnDateSetListener ttanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keluar_barang);

        dbHelper = new DataHelper(this);
        nama =  findViewById(R.id.etkb1);
        jumlah = findViewById(R.id.etkb2);
        tanggal = findViewById(R.id.etkb5);
        jumlah2 = findViewById(R.id.etkb10);
        ton1 = findViewById(R.id.ton1);
        jummm = findViewById(R.id.textView111);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT nama_barang, (jumlah_barang_bagus + jumlah_barang_rusak) as jumlahbt FROM barang WHERE id_barang ='" +
                getIntent().getStringExtra("id_barang") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
        }
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            jummm.setText(cursor.getString(1).toString());
        }


        //text view pilih tanggal
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                @SuppressLint("WrongConstant") int month = cal.get(Calendar.MONDAY);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogd = new DatePickerDialog(
                        KeluarBarang.this,
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





        //tombol simpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                SQLiteDatabase dbs = dbHelper.getReadableDatabase();
                cursor1 = dbs.rawQuery("SELECT jumlah_barang_bagus, jumlah_barang_rusak FROM barang WHERE id_barang ='" +
                        getIntent().getStringExtra("id_barang") + "'", null);
                cursor1.moveToFirst();
                int num1 = Integer.parseInt(cursor1.getString(0).toString());
                int num2 = Integer.parseInt(jumlah.getText().toString());
                int num3 = Integer.parseInt(cursor1.getString(1).toString());
                int num4 = Integer.parseInt(jumlah2.getText().toString());
                Boolean str = jumlah.getText().toString().equals("");
                if (num2 > num1){
                    Toast.makeText(getApplicationContext(),"Jumlah Yang Dimasukkan Melebihi Stok Yang Tersedia", Toast.LENGTH_SHORT).show();
                    return;
                }else if (num4 > num3){
                    Toast.makeText(getApplicationContext(),"Jumlah Yang Dimasukkan Melebihi Stok Yang Tersedia", Toast.LENGTH_SHORT).show();
                    return;
                }
                int tot = num1 - num2;
                int tot2 = num3 - num4;


                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (tanggal.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Tolong Pilih Tanggal Barang Keluar", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.execSQL("insert into transaksi_keluar(id_barang, tanggal_tk, jumlah_barangbk, jumlah_barangrk) values('" +
                        getIntent().getStringExtra("id_barang") + "','" +
                        tanggal.getText().toString() + "','" +
                        jumlah.getText().toString() + "','" +
                        jumlah2.getText().toString() + "')");
                db.execSQL("update barang set jumlah_barang_bagus='"+
                                tot +"', jumlah_barang_rusak='"+ tot2+"' where id_barang ='"+
                                getIntent().getStringExtra("id_barang") + "'");
                Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(KeluarBarang.this, HomeAdmin.class);
                startActivity(i);
            }
        });



    }
}
