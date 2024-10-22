package com.example.tp2spark;

// import org.sql2o.Sql2o;

import static spark.Spark.*;

import com.example.tp2spark.controllers.ControllerContrato;
import com.example.tp2spark.controllers.ControllerPropiedades;

public class Main {
    public static void main(String[] args) {
        get("/propiedades", ControllerPropiedades.getPropiedades);
        get("/propiedades/:id", ControllerPropiedades.getPropiedad);
        // post("/propiedades", ControllerPropiedades.addPropiedad);
        // put("/propiedades", ControllerPropiedades.updatePropiedad);
        // delete("/propiedades", ControllerPropiedades.deletePropiedad);
        // contrato
        // get("/contrato", ControllerContrato.getContratos);
        // get("/contrato/:id", ControllerContrato.getContrato);
        post("/contrato", ControllerContrato.addContrato);
        // try {
        // Sql2o sql2o = DbConexion.getSql2o();
        // System.out.println("conexion a la base de datos exitosa");
        // } catch (Exception e) {
        // System.out.println("error al conextarse a la base de datos");
        // }
    }
}