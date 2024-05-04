package com.example.l4.service;

import com.example.l4.entity.Ciudad;
import com.example.l4.entity.CiudadDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CiudadService {

    @GET("/geo/1.0/direct?q=London&limit=1&appid=8dd6fc3be19ceb8601c2c3e811c16cf1")
    Call<List<Ciudad>> obtenerCiudad();

    @GET("/geo/1.0/direct")
    Call<List<Ciudad>> obtenerCiudad2(@Query("q") String city, @Query("limit") int one, @Query("appid") String api);
}
