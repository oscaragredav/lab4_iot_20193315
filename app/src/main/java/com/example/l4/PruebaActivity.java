package com.example.l4;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.l4.R;
import com.example.l4.databinding.ActivityPruebaBinding;
import com.example.l4.entity.Ciudad;
import com.example.l4.entity.CiudadDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PruebaActivity extends AppCompatActivity {

    CiudadService ciudadService;
    ActivityPruebaBinding binding;
    private static String TAG = "msg-mainAct";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);


    }

    //llamado al retro fit
    public void createRetrofitService() {
         ciudadService= new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/geo/1.0/direct?")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CiudadService.class);
    }

    public void cargarListaWebService() {
        ciudadService.obtenerCiudad().enqueue(new Callback<CiudadDto>() {
            @Override
            public void onResponse(Call<CiudadDto> call, Response<CiudadDto> response) {
                if (response.isSuccessful()) {
                    CiudadDto body = response.body();
                    List<Ciudad> lista = body.getLista();
                    //tengo la lista -> ready!
                    CiudadAdapter ciudadAdapter = new CiudadAdapter(getApplicationContext(),lista);
                    ciudadAdapter.setContext(getApplicationContext());
                    ciudadAdapter.setList(lista);

                    binding.recyclerViewCiudad.setAdapter(ciudadAdapter);
                    binding.recyclerViewCiudad.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                } else {
                    Log.d(TAG, "response unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<CiudadDto> call, Throwable t) {
                Log.d(TAG, "algo pasó!!!");
                Log.d(TAG, t.getMessage());
                t.printStackTrace();

            }
        });
    }


}