package com.example.tp2spark;

import static spark.Spark.*;

import com.example.tp2spark.controllers.ControllerContrato;
import com.example.tp2spark.controllers.ControllerPropiedades;

public class Main {
    public static void main(String[] args) {
        get("/propiedades", ControllerPropiedades.getPropiedades);
        get("/propiedades/:id", ControllerPropiedades.getPropiedad);
        post("/contrato", ControllerContrato.addContrato);

        // Segunda entrega
        post("/propiedad", ControllerPropiedades.addPropiedad);
        get("/contrato/:CUIL", ControllerContrato.getContrato);

    }
}