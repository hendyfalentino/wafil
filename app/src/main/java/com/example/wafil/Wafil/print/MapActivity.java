package com.example.wafil.Wafil.print;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.wafil.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;
import com.sucho.placepicker.MapType;
import com.sucho.placepicker.PlacePicker;

public class MapActivity extends AppCompatActivity {

    int PERMISSION_ID = 44;

    //pakai FusedLocationProvider API untuk mendapatkan posisi pengguna
    FusedLocationProviderClient fusedLocationProviderClient;

    Double latitude, longtitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_lokasi);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        ambilLokasiTerakhir();

         // Saat tombol pilih tempat di-klik
        findViewById(R.id.open_place_picker_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new PlacePicker.IntentBuilder()
                        .setLatLong(latitude, longtitude)
                        .showLatLong(true)
                        .setMapRawResourceStyle(R.raw.map_style)
                        .setMapType(MapType.NORMAL)
                        .setPlaceSearchBar(true, "AIzaSyBY8gTcaaQ6tuBkCAMHrfAe_rbpcAIdx1U")
                        .build(MapActivity.this);

                startActivityForResult(intent, Constants.PLACE_PICKER_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                try {
                    // ambil address
                    AddressData addressData = data.getParcelableExtra(Constants.ADDRESS_INTENT);

                    // pass ke string
                    ((TextView) findViewById(R.id.address_data_text_view)).setText(addressData.toString());
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage());
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    // cek apakah user memberikan akses atau tidak
    private boolean cekIzinLokasi() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    // minta izin akses pada user jika belum diizinkan
    private void mintaIzinLokasi() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    // method dipanggil setelah user menerima/menolak akses
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //diijinkan. mulai pencarian informasi lokasi
                ambilLokasiTerakhir();
            }
        }
    }

    // cek status lokasi atau GPS menyala atau tidak
    private boolean cekStatusLokasi() {
        // cek location service
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // return 1 jika gps menyala, 0 jika tidak atau hanya menggunakan internet
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER
                );
    }

    @SuppressLint("MissingPermission")
    private void ambilLokasiTerakhir() {

        // cek jika user memberi izin
        if (cekIzinLokasi()) {
            // cek jika GPS menyala
            if (cekStatusLokasi()) {
                // ambil lokasi terakhir
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                // simpan lokasi
                                Location location = task.getResult();

                                // kondisi jika lokasi kosong
                                if (location == null) {
                                    // jika lokasi kosong, minta lokasi baru
                                    mintaLokasiBaru();
                                } else {
                                    // tampilkan koordinat pada textview
//                                    latiTv.setText(location.getLatitude() + "");
//                                    longTv.setText(location.getLongitude() + "");
                                    latitude = location.getLatitude();
                                    longtitude = location.getLongitude();
                                }
                            }
                        }
                );
            } else {
                // toast message untuk memperingatkan pengguna menyalakan lokasi
                Toast.makeText(this, "Nyalakan GPS/Lokasi anda", Toast.LENGTH_LONG).show();

                // prompt nyalakan lokasi
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // minta akses untuk mengizinkan akses lokasi
            mintaIzinLokasi();
        }
    }

    // alternatif atau jaga-jaga jika informasi lokasi hilang atau user tidak pernah menyalakan lokasi sebelumnya
    @SuppressLint("MissingPermission")
    private void mintaLokasiBaru() {
        LocationRequest locationRequest = new LocationRequest();

        // cari lokasi dengan akurasi tertinggi
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // waktu interval maksimal untuk update lokasi, 10000 = 10 detik
        locationRequest.setInterval(10000);

        // waktu interval minimal untuk update lokasi, 5000 = 5 detik
        locationRequest.setFastestInterval(5000);

        // 1 = realtime update lokasi
        locationRequest.setNumUpdates(1);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(
                locationRequest, locationCallback,
                Looper.myLooper()
        );
    }

    // ketika lokasi terupdate maka akan memanggil method callback ini
    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            // ambil lokasi terakhir
            Location lastLocation = locationResult.getLastLocation();

            // tampilkan koordinat pada textview
//            latiTv.setText(lastLocation.getLatitude() + "");
//            longTv.setText(lastLocation.getLongitude() + "");
            latitude = lastLocation.getLatitude();
            longtitude = lastLocation.getLatitude();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (cekIzinLokasi()) {
            ambilLokasiTerakhir();
        }
    }

}
