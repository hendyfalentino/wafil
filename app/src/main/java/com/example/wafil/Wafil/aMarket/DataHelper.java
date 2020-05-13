package com.example.wafil.Wafil.aMarket;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "stokbarang.db";
    private static final int DB_VER = 1;
    public DataHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql2 = "create table pembeli(id_pembeli integer primary key autoincrement, nama_pembeli text, alamat text, no_telp text, username text, password text)";
        String sql1 = "create table barang(id_barang integer primary key autoincrement, nama_barang text, jenis_barang text, deskripsi text, harga integer);";
        String sql3 = "create table transaksi(id_transasksi integer primary key autoincrement, id_stok integer, id_pembeli integer, beli integer, total integer,status text, tgl_bayar datetime, tgl_kirim datetime)";
        String sql4 = "create table stok(id_stok integer primary key autoincrement, id_barang integer, id_provider integer, jumlah)";
        String sql5 = "create table provider(id_provider integer primary key autoincrement, nama_provider text, nama_toko text, alamat_toko text, no_telp text, username text, password text)";
        String sql6 = "insert into pembeli(nama_pembeli, alamat, no_telp, username, password) values ('Andre Kanalung', 'manado', '0821498681', 'admin', 'admin')";
        String sql7 = "insert into barang(nama_barang, jenis_barang, deskripsi, harga) values ('Daging Sapi', 'Daging', 'Daging Impor Pilihan', '50000')";
        String sql8 = "insert into provider(nama_provider, nama_toko, alamat_toko, no_telp, username, password) values ('Eka Putra', 'Sinar Mas', 'Manado', '081124555681', 'eka', '12345')";
        String sql9 = "insert into stok(id_barang, id_provider, jumlah) values ('1', '1', '10')";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);
        db.execSQL(sql7);
        db.execSQL(sql8);
        db.execSQL(sql9);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
