package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    public static final String EXTRA_CITY_NAME = "city_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        String cityName = getIntent().getStringExtra(EXTRA_CITY_NAME);

        TextView cityText = findViewById(R.id.text_city_name);
        cityText.setText(cityName != null ? cityName : "");

        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                finish(); // go back to MainActivity
            }
        });
    }
}
