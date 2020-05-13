package com.example.wafil.Wafil.basBangunan.memesan_Jasa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wafil.Wafil.basBangunan.BasBangunanMainActivity;
import com.example.wafil.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Pesan_Jasa extends AppCompatActivity {
    Intent intent;
    Uri fileUri;
    Bitmap bitmap, decoded;
    public final int REQUEST_CAMERA = 0;
    public final int SELECT_FILE = 1;

    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;
    Button btn_selanjutnya;
    ImageButton ambil_foto;
    ImageView hasil_foto;
    TextView jenis_masalah, harga;

    private String KEY_NAME = "NAME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan__jasa);



        hasil_foto = findViewById(R.id.tampil_foto_kerusakan);

        jenis_masalah = findViewById(R.id.jenis_masalah);
        jenis_masalah.setText(getIntent().getType());


        btn_selanjutnya = findViewById(R.id.btn_selanjutnya);
        btn_selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(Pesan_Jasa.this, Pilih_Bas_Bangunan.class);
                startActivity(pindah);
            }
        });

        ambil_foto = findViewById(R.id.tambah_foto_kerusakan);
        ambil_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
//untuk izin kamera spy nda error
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


    }

    private void selectImage() {
        hasil_foto.setImageResource(0);
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Pesan_Jasa.this);
        builder.setTitle("Add Photo!");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                    fileUri = getOutputMediaFileUri();
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(intent, REQUEST_CAMERA);

                    /*
                   fileUri= Uri.fromFile(getOutputMediaFile());
                    startActivityForResult(intent, REQUEST_CAMERA);*/

                } else if (items[item].equals("Choose from Library")) {
                    intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult", "requestCode " + requestCode + ", resultCode " + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                try {
                    Log.e("CAMERA", fileUri.getPath());

                    bitmap = BitmapFactory.decodeFile(fileUri.getPath());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
                try {
                    // mengambil gambar dari Gallery
                    bitmap = MediaStore.Images.Media.getBitmap(Pesan_Jasa.this.getContentResolver(), data.getData());
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Untuk menampilkan bitmap pada ImageView
    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));

        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        hasil_foto.setImageBitmap(decoded);
    }

    // Untuk resize bitmap
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    private static File getOutputMediaFile( ) {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "DeKa");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("Monitoring", "Oops! Failed create Monitoring directory");
                return null;
            }
        }


        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_DeKa_" + timeStamp + ".jpg");

        return mediaFile;
    }

}