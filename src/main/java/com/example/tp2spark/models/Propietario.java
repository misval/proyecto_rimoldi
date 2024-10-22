package com.example.tp2spark.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Propietario extends Persona {
    private String CBU;

    public Propietario(String cBU, String CUIL,
            String DNI,
            String nombre,
            String email,
            String fechaNacimiento) {

        super.CUIL = CUIL;
        super.DNI = DNI;
        super.nombre = nombre;
        super.email = email;
        super.fechaNacimiento = fechaNacimiento;
        this.CBU = cBU;

    }

}
