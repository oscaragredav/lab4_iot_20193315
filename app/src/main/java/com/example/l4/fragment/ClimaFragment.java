package com.example.l4.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.l4.R;
import com.example.l4.databinding.FragmentClimaBinding;
import com.example.l4.entity.Clima;
import com.example.l4.adapter.ClimaAdapter;
import com.example.l4.service.GeoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ClimaFragment extends Fragment {

    FragmentClimaBinding bin;
    GeoService geoService;
    private static String TAG = "tag-error";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bin = FragmentClimaBinding.inflate(inflater,container,false);


        geoService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GeoService.class);

        bin.buscarClima.setOnClickListener(v -> {
            double lat = Double.parseDouble(bin.editText1.getText().toString());
            double lon = Double.parseDouble(bin.editText2.getText().toString());
            geoService.obtenerClima(lat,lon,"8dd6fc3be19ceb8601c2c3e811c16cf1").enqueue(new Callback<Clima>() {
                @Override
                public void onResponse(Call<Clima> call, Response<Clima> response) {

                    if (response.isSuccessful()){
//                        Toast.makeText(getContext(), "isSuccessful onResponse", Toast.LENGTH_SHORT).show();
                        Clima clima = response.body();
                        ClimaAdapter climaAdapter = new ClimaAdapter();
                        climaAdapter.setContext(getContext());
                        climaAdapter.setClima(clima);

                        bin.recyclerViewClima.setAdapter(climaAdapter);
                        bin.recyclerViewClima.setLayoutManager(new LinearLayoutManager(getContext()));

//                        bin.textView3.setText(climaAdapter.getClima().getMain().getTemp_max());

                    }else{
                        Log.e(TAG, "error onResponse");
                        Toast.makeText(getContext(), "error onResponse", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Clima> call, Throwable t) {
                    Log.e(TAG, "error onFailure");
                    Toast.makeText(getContext(), "error onResponse", Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        });

        return bin.getRoot();
    }
}