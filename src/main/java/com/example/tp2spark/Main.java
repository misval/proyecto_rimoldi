package com.example.tp2spark;

// import org.sql2o.Sql2o;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        get("/propiedades", Controller.getPropiedades);
        get("/propiedades/:id", Controller.getPropiedad);
        post("/propiedades", Controller.addPropiedad);
        put("/propiedades", Controller.updatePropiedad);
        delete("/propiedades", Controller.deletePropiedad);
        // try {
        //     Sql2o sql2o = DbConexion.getSql2o();
        //     System.out.println("conexion a la base de datos exitosa");
        // } catch (Exception e) {
        //     System.out.println("error al conextarse a la base de datos");
        // }
    }
}