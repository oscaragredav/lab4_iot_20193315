package com.example.l4.service;

import com.example.l4.entity.Ciudad;
import com.example.l4.entity.Clima;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoService {

    @GET("/geo/1.0/direct")
    Call<List<Ciudad>> obtenerCiudad3(@Query("q") String city, @Query("limit") int one, @Query("appid") String api);

    @GET("/data/2.5/weather")
    Call<Clima> obtenerClima(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String api);


}
