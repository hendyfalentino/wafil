package com.example.wafil.Wafil.aMarket;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeAdmin extends AppCompatActivity {
    protected Cursor cursor;
    String[] daftar;
    String[] daftar1;
    ListView ListView01;
    com.example.wafil.Wafil.aMarket.DataHelper dbcenter;
    public static HomeAdmin ha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        nav();

        ha = this;
        dbcenter = new com.example.wafil.Wafil.aMarket.DataHelper(this);
        RefreshList();
    }

    //tampilan data barang pada home
    public void RefreshList(){
        final SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM barang", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int cc = 0; cc < cursor.getCount(); cc++)
        {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
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
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3){
                final String selection = daftar1[arg2];
                final CharSequence[] dialogitem = {"Lihat Barang", "Beli Barang"};
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeAdmin.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0:
                                Intent intent = new Intent(getApplicationContext(),com.example.wafil.Wafil.aMarket.LihatBarang.class);
                                intent.putExtra("id_barang", selection);
                                startActivity(intent);
                                break;
                            case 1:
                                db.execSQL("INSERT INTO transaksi(id_stok, id_pembeli, beli, total, status, tgl_bayar, tgl_kirim) values('1', '1', '1', '50000', 'belum_bayat', '2020-05-14', '0000-00-00')");
                                Intent intent1 = new Intent(getApplicationContext(), com.example.wafil.Wafil.aMarket.TambahBarang.class);
                                intent1.putExtra("id_barang", selection);
                                startActivity(intent1);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();

    }


    //navigasi menu
    public void nav(){
        BottomNavigationView botnav = findViewById(R.id.botnvbar);
        Menu menu = botnav.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.ic_home:
                    break;
                case R.id.ic_kb:
                    Intent i2 = new Intent(HomeAdmin.this, com.example.wafil.Wafil.aMarket.TambahBarang.class);
                    startActivity(i2);
                    break;
                case R.id.ic_out:
                    Intent i1 = new Intent(HomeAdmin.this, com.example.wafil.Wafil.aMarket.LoginActivity.class);
                    startActivity(i1);
                    break;
                }
                return false;
            }
        });
    }
}
