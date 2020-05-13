package com.example.wafil.Wafil.aMarket;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TambahBarang extends AppCompatActivity {
    protected Cursor cursor;
    String[] daftar;
    String[] daftar1;
    ListView ListView01;
    DataHelper dbcenter;
    public static TambahBarang ha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        nav();

        ha = this;
        dbcenter = new DataHelper(this);
        RefreshList();
    }

    //tampilan data barang pada home
    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT barang.nama_barang FROM transaksi INNER JOIN stok INNER JOIN barang ON transaksi.id_stok=stok.id_stok AND stok.id_barang=barang.id_barang", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int cc = 0; cc < cursor.getCount(); cc++)
        {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0).toString();
        }


        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);

        daftar1 = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int dd = 0; dd < cursor.getCount(); dd++)
        {
            cursor.moveToPosition(dd);
            daftar1[dd] = cursor.getString(0).toString();
        }
    }
    /*    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1;
    EditText  nama, jumlahbb, jumlahbr, jenis;
    TextView tanggal;
    private DatePickerDialog.OnDateSetListener ttanggal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);
        this.setTitle("Tambah Barang");
        nav();

        dbHelper = new DataHelper(this);
        nama =  findViewById(R.id.ettb1);
        jumlahbb =  findViewById(R.id.ettb2);
        jumlahbr = findViewById(R.id.ettb3);
        jenis = findViewById(R.id.ettb4);
        tanggal = findViewById(R.id.ettb5);
        ton1 = findViewById(R.id.ton1);

        //text view pilih tanggal
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONDAY);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialogd = new DatePickerDialog(
                        TambahBarang.this,
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
                Log.d("TB", "Tanggal = " + year + "/" + month + "/" + day);

                String tanggal1 = year + "/" + month + "/" + day;
                tanggal.setText(tanggal1);
            }
        };




        //tombol simpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if (nama.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Tolong Isi Nama Barang", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (jumlahbb.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Tolong Isi Jumlah Barang Bagus", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (jumlahbr.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Tolong Isi Jumlah Barang Rusak", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (jenis.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Tolong Isi Jenis Barang", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (tanggal.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Tolong Isi Tanggal", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.execSQL("insert into barang(tanggal, nama_barang, jumlah_barang_bagus, jumlah_barang_rusak, jenis_barang) values('" +
                        tanggal.getText().toString() + "','" +
                        nama.getText().toString() + "','" +
                        jumlahbb.getText().toString() + "','" +
                        jumlahbr.getText().toString() + "','" +
                        jenis.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(TambahBarang.this, HomeAdmin.class);
                startActivity(i);
            }
        });


    }
*/
    public void nav(){
        BottomNavigationView botnav = findViewById(R.id.botnvbar);
        Menu menu = botnav.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_home:
                        Intent i1 = new Intent(TambahBarang.this, HomeAdmin.class);
                        startActivity(i1);
                        break;
                    case R.id.ic_kb:
                        break;
                    case R.id.ic_out:
                        Intent i3 = new Intent(TambahBarang.this, LoginActivity.class);
                        startActivity(i3);
                        break;
                }

                return false;
            }
        });
    }
}
