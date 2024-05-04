package com.example.l4;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.l4.databinding.ActivityPruebaBinding;
import com.example.l4.entity.Ciudad;
import com.example.l4.service.CiudadService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PruebaActivity extends AppCompatActivity {

    CiudadService ciudadService;
    ActivityPruebaBinding binding;
    private static String TAG = "eeerror";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);

        ciudadService= new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CiudadService.class);

        binding.button.setOnClickListener(v -> {
            String city = binding.editText.getText().toString();

            ciudadService.obtenerCiudad2(city,1,"8dd6fc3be19ceb8601c2c3e811c16cf1").enqueue(new Callback<List<Ciudad>>() {
                @Override
                public void onResponse(@NonNull Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
                    if (response.isSuccessful()) {
                        List<Ciudad> ciudads = response.body();
                        //tengo la lista -> ready!
                        CiudadAdapter ciudadAdapter = new CiudadAdapter();
                        ciudadAdapter.setContext(PruebaActivity.this);
                        ciudadAdapter.setList(ciudads);

                        binding.recyclerViewCiudad.setAdapter(ciudadAdapter);
                        binding.recyclerViewCiudad.setLayoutManager(new LinearLayoutManager(PruebaActivity.this));


                    } else {
                        Log.e(TAG, "response unsuccessful");
                    }
                }

                @Override
                public void onFailure(Call<List<Ciudad>> call, Throwable t) {
                    Log.e(TAG, "algo pasó!!!");
                    Log.e(TAG, t.getMessage());
                    t.printStackTrace();

                }
            });
        });



    }


}