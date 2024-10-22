package com.example.tp2spark.models;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Inquilino extends Persona {
    private ArrayList<Contrato> contratos = new ArrayList<Contrato>();
    private char mascotas; // booleano traeria problemas
    private double ingresos;
    private String empresaTrabajo;
    private int cantidadIntegrantes;

    public Inquilino(String CUIL,
            String DNI,
            String nombre,
            String email,
            String fechaNacimiento,
            char mascotas,
            String empresaTrabajo,
            int cantidadIntegrantes,
            double ingresos) {
        super.CUIL = CUIL;
        super.DNI = DNI;
        super.nombre = nombre;
        super.email = email;
        super.fechaNacimiento = fechaNacimiento;
        this.mascotas = mascotas;
        this.empresaTrabajo = empresaTrabajo;
        this.cantidadIntegrantes = cantidadIntegrantes;
        this.ingresos = ingresos;
    }

    public void agregarContrato(Contrato contrato) {
        this.contratos.add(contrato);
    }
}
