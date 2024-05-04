package com.example.l4;

import com.example.l4.entity.CiudadDto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CiudadService {

    @GET("q=London&limit=1&appid=8dd6fc3be19ceb8601c2c3e811c16cf1")
    Call<CiudadDto> obtenerCiudad();
}
