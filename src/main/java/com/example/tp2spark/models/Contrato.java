package com.example.tp2spark.models;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Contrato {
    private Propiedad propiedad;
    private ArrayList<Garante> garantes = new ArrayList<Garante>();
    private int idContrato;
    private String fechaInicio;
    private String fechaFin;
    private String FechaCancelacion;

    public Contrato(int idContrato, String fechaInicio, String fechaFin, String fechaCancelacion) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        FechaCancelacion = fechaCancelacion;
    }

    public void agregarPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    public void agregarGarante(Garante garante) {
        this.garantes.add(garante);
    }
}
