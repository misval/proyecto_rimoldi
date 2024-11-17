package com.example.tp2spark.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Contrato {
    private int idContrato;
    private Propiedad propiedad;
    private List<Garante> garantes = new ArrayList<Garante>();
    private Inquilino inquilino;
    private String fechaInicio;
    private String fechaFin;
    private String FechaCancelacion;

    public Contrato(String fechaInicio, String fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public void agregarPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public void agregarInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public void agregarGarante(Garante garante) {
        this.garantes.add(garante);
    }
}
