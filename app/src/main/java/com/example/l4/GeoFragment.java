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
import com.example.l4.service.CiudadService;
import com.example.l4.service.GeoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GeoFragment extends Fragment {
    FragmentGeoBinding binding;
//    CiudadService ciudadService;
    GeoService geoService;
    private static String TAG = "tag-error";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGeoBinding.inflate(inflater,container,false);

        geoService = new Retrofit.Builder()
//        ciudadService = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GeoService.class);

        binding.button.setOnClickListener(v -> {

            String city = binding.editText.getText().toString();
            geoService.obtenerCiudad3(city,1,"8dd6fc3be19ceb8601c2c3e811c16cf1").enqueue(new Callback<List<Ciudad>>() {
                @Override
                public void onResponse(Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
                    if (response.isSuccessful()){
                        List<Ciudad> ciudads = response.body();
                        CiudadAdapter ciudadAdapter = new CiudadAdapter();
                        ciudadAdapter.setContext(getContext());
                        ciudadAdapter.setList(ciudads);

                        binding.recyclerViewCiudad.setAdapter(ciudadAdapter);
                        binding.recyclerViewCiudad.setLayoutManager(new LinearLayoutManager(getContext()));
                    }else{
                        Log.e(TAG, "error onResponse");
                    }
                }

                @Override
                public void onFailure(Call<List<Ciudad>> call, Throwable t) {
                    Log.e(TAG, "error onFailure");
                    t.printStackTrace();
                }
            });
            binding.editText.setText("hola");
        });



        return binding.getRoot();
    }


}