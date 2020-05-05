package com.example.wafil.Wafil.chilyoHouze;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wafil.R;

public class activity_chilyo_rating extends AppCompatActivity {

    private RatingBar ratingBar;
    /* private TextView result; */
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chilyo_rating);

        ratingBar = findViewById(R.id.ratingBar_id);
        button = (Button)findViewById(R.id.button);

        /*
        result.setText("Rating : "+ratingBar.getRating());
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                result.setText("Rating : "+rating);
            }
        });*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
