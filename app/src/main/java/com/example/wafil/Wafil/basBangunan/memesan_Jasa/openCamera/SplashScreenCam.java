package com.example.wafil.Wafil.basBangunan.memesan_Jasa.openCamera;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;
import com.example.wafil.Wafil.basBangunan.memesan_Jasa.Pesan_Jasa;
import com.example.wafil.Wafil.basBangunan.memesan_Jasa.openCamera.helper.PermissionHelper;


public class SplashScreenCam extends AppCompatActivity {

    PermissionHelper permissionHelper;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionHelper = new PermissionHelper(this);

        checkAndRequestPermissions();
    }

    private boolean checkAndRequestPermissions() {
        permissionHelper.permissionListener(new PermissionHelper.PermissionListener() {
            @Override
            public void onPermissionCheckDone() {
                intent = new Intent(SplashScreenCam.this, Pesan_Jasa.class);
                startActivity(intent);
                finish();
            }
        });

        permissionHelper.checkAndRequestPermissions();

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        permissionHelper.onRequestCallBack(requestCode, permissions, grantResults);
    }
}

