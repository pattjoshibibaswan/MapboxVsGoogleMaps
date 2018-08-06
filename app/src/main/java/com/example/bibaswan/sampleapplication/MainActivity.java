package com.example.bibaswan.sampleapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText latitude,longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitude = findViewById(R.id.lat_e_txt);
        longitude = findViewById(R.id.long_e_txt);
    }

    public void onSubmitClick(View view) {
        Intent intent = new Intent(this,MapActivity.class);
        intent.putExtra(MapActivity.LATITIDE,latitude.getText().toString());
        intent.putExtra(MapActivity.LONGITUDE,longitude.getText().toString());
        startActivity(intent);
    }
}
