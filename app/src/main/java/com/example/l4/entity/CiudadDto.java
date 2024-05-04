package com.example.l4.entity;

import java.util.List;

public class CiudadDto {
    public List<Ciudad> lista;

    public CiudadDto(List<Ciudad> lista) {
        this.lista = lista;
    }

    public List<Ciudad> getLista() {
        return lista;
    }

    public void setLista(List<Ciudad> lista) {
        this.lista = lista;
    }
}
