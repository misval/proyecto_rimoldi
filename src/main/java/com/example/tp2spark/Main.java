package com.example.tp2spark;

// import org.sql2o.Sql2o;

import static spark.Spark.*;

import com.example.tp2spark.controllers.ControllerContrato;
import com.example.tp2spark.controllers.ControllerPropiedades;

public class Main {
    public static void main(String[] args) {
        get("/propiedades", ControllerPropiedades.getPropiedades);
        get("/propiedades/:id", ControllerPropiedades.getPropiedad);

        // contrato
        post("/contrato", ControllerContrato.addContrato);
        get("/contrato/:CUIL", ControllerContrato.getContrato);

    }
}