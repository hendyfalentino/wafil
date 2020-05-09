package com.example.wafil.Wafil.print;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.sucho.placepicker.AddressData;
import com.sucho.placepicker.Constants;
import com.sucho.placepicker.MapType;
import com.sucho.placepicker.PlacePicker;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_main);

//        Saat tombol pilih tempat di-klik
        findViewById(R.id.open_place_picker_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new PlacePicker.IntentBuilder()
                        .setLatLong(1.504533, 124.908277)
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
}
