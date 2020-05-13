package com.example.wafil.Wafil.aMarket;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class LihatBarang extends AppCompatActivity {
    protected Cursor cursor;
    TextView nama, jumlahbb, jumlahbr, jumlahbt, jenis, tanggal;
    DataHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barang);

        nama = findViewById(R.id.tvlb1);
        jumlahbb = findViewById(R.id.tvlb2);
        jumlahbr = findViewById(R.id.tvlb3);
        jumlahbt = findViewById(R.id.tvlb4);
        jenis = findViewById(R.id.tvlb5);
        tanggal = findViewById(R.id.tvlb6);
        dbhelper = new DataHelper(this);

        SQLiteDatabase db = dbhelper.getReadableDatabase();
        cursor = db.rawQuery("select nama_barang, tanggal, jumlah_barang_bagus, jumlah_barang_rusak, (jumlah_barang_bagus + jumlah_barang_rusak) as jumlahbt, jenis_barang from barang WHERE id_barang = '" +
                getIntent().getStringExtra("id_barang") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            nama.setText(cursor.getString(0).toString());
            tanggal.setText(cursor.getString(1).toString());
            jumlahbb.setText(cursor.getString(2).toString());
            jumlahbr.setText(cursor.getString(3).toString());
            jumlahbt.setText(cursor.getString(4).toString());
            jenis.setText(cursor.getString(5).toString());



        }

    }
}

