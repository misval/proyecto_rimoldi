package com.example.tp2spark;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

public class Main {
    public static void main(String[] args) {
        PropiedadControlador controlador = new PropiedadControlador();
        get("/propiedades", controlador::getPropiedades);
        get("/propiedades/:id", controlador::getPropiedad);
        post("/propiedades", controlador::addPropiedad);
        put("/propiedades", controlador::updatePropiedad);
        delete("/propiedades", controlador::deletePropiedad);
    }
}