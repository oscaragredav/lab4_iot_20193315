package com.example.l4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.l4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.ingresar.setOnClickListener(v -> {
            if(tengoInternet()){
                Intent intent = new Intent(MainActivity.this, appActivity.class);
                startActivity(intent);
            }else{
                conexion();
            }
        });

    }

    public void app(View view){
        Intent intent = new Intent(this, appActivity.class);
        startActivity(intent);
    }

    public void prueba(View view){
        Intent intent = new Intent(this, PruebaActivity.class);
        startActivity(intent);
    }

    public boolean tengoInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        Log.d("msg-test-internet", "Internet: " + tieneInternet);
        return tieneInternet;
    }

    private void conexion() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Conexión")
                .setMessage("No se pudo establecer la conexión con internet")
                .setPositiveButton("Configuración", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id) {
                        config();
                    }
                })
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false);
        AlertDialog dialog = b.create();
        dialog.show();
    }

    private void config() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }
}