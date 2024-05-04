package com.example.l4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void appActivity(View view){
        Intent intent = new Intent(this, appActivity.class);
        startActivity(intent);
    }public void pruebaActivity(View view){
        Intent intent = new Intent(this, PruebaActivity.class);
        startActivity(intent);
    }
}