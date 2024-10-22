package com.example.tp2spark.models;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import lombok.Data;

@Data
public class Persona {
    private String CUIL;
    private String DNI;
    private String nombre;
    private String email;
    private String fechaNacimiento;
}
