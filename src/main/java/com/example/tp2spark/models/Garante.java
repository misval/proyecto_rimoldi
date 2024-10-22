package com.example.tp2spark.models;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Garante extends Persona {
    private ArrayList<Contrato> contratos = new ArrayList<Contrato>();
    private double ingresos;
    private String trabajo;
    private String emailTrabajo;

    public Garante(String CUIL,
            String DNI,
            String nombre,
            String email,
            String fechaNacimiento,
            double ingresos, String trabajo, String emailTrabajo) {
        super.CUIL = CUIL;
        super.DNI = DNI;
        super.nombre = nombre;
        super.email = email;
        super.fechaNacimiento = fechaNacimiento;
        this.ingresos = ingresos;
        this.trabajo = trabajo;
        this.emailTrabajo = emailTrabajo;
    }

    public void agregarContrato(Contrato contrato) {
        this.contratos.add(contrato);
    }
}
