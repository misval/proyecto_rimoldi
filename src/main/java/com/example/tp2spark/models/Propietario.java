package com.example.tp2spark.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Propietario extends Persona {
    private String CBU;

}
