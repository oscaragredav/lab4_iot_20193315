package com.example.l4.entity;

import java.util.List;

public class ClimaDto {

    private List<Clima> lista;

    public ClimaDto(List<Clima> lista) {
        this.lista = lista;
    }

    public List<Clima> getLista() {
        return lista;
    }

    public void setLista(List<Clima> lista) {
        this.lista = lista;
    }

}
