package com.example.l4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.l4.databinding.FragmentGeoBinding;
import com.example.l4.entity.Ciudad;
import com.example.l4.entity.CiudadDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GeoFragment extends Fragment {
    FragmentGeoBinding binding;
    CiudadService ciudadService;
    private static String TAG = "msg-mainAct";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGeoBinding.inflate(inflater,container,false);




        return binding.getRoot();
    }


    //retrofit
    public void createRetrofitService() {
        ciudadService = new Retrofit.Builder()
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
                    List<Ciudad> list = body.getLista();


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