package com.example.tp2spark.controllers;

import com.example.tp2spark.DAOs.DAOPropiedad;
import com.example.tp2spark.DAOs.DAOPropietario;
import com.google.gson.Gson;

import spark.Route;
import spark.Request;
import spark.Response;

public class ControllerPropiedades {
  private static DAOPropiedad servicioPropiedad = new DAOPropiedad();
  private static DAOPropietario servicioPropietario = new DAOPropietario();
  private final static Gson gson = new Gson();

  public static Route getPropiedades = (Request req, Response res) -> {
    try {
      res.status(200);
      return gson.toJson(servicioPropiedad.getAllPropiedades());
    } catch (Exception e) {
      res.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
  };

  public static Route getPropiedad = (Request req, Response res) -> {
    try {
      res.status(200);
      String id = req.params(":id");
      return gson.toJson(servicioPropiedad.getPropiedadById(id));
    } catch (Exception e) {
      res.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
  };

  // public static Route addPropiedad = (Request req, Response res) -> {
  // String idPropietario = req.queryParams("idPropietario");
  // System.out.println(idPropietario);
  // Propietario propietario = servicioPropietario.getPropietario(idPropietario);
  // System.out.println(propietario);
  // Integer id = (int) (Math.random() * 100000);
  // String ubicacion = req.queryParams("ubicacion");
  // String tipoPropiedad = req.queryParams("tipo").toUpperCase();
  // String destinoPropiedad = req.queryParams("destino").toUpperCase();
  // Integer ambientes = Integer.valueOf(req.queryParams("ambientes"));
  // Integer banios = Integer.valueOf(req.queryParams("banios"));
  // Integer mts_cuadrados = Integer.valueOf(req.queryParams("mtsCuadrados"));

  // Propiedad propiedad = new Propiedad(propietario, id, ubicacion,
  // tipoPropiedad, destinoPropiedad, ambientes, banios,
  // mts_cuadrados);

  // try {
  // res.status(200);
  // return gson.toJson(servicioPropiedad.addPropiedad(propiedad));
  // } catch (Exception e) {
  // res.status(400);
  // return new Gson().toJson("Error controlador: " + e.getMessage());
  // }
  // };

  // public static Route updatePropiedad = (Request req, Response res) -> {
  // Integer id = Integer.valueOf(req.queryParams("id"));
  // String ubicacion = req.queryParams("ubicacion");
  // String tipoPropiedad = req.queryParams("tipo").toUpperCase();
  // String destinoPropiedad = req.queryParams("destino").toUpperCase();
  // Integer ambientes = Integer.valueOf(req.queryParams("ambientes"));
  // Integer banios = Integer.valueOf(req.queryParams("banios"));
  // Integer mts_cuadrados = Integer.valueOf(req.queryParams("mtsCuadrados"));

  // Propiedad propiedad = new Propiedad(id, ubicacion, tipoPropiedad,
  // destinoPropiedad, ambientes, banios,
  // mts_cuadrados);
  // try {
  // res.status(200);
  // return gson.toJson(servicioPropiedad.updatePropiedad(propiedad));
  // } catch (Exception e) {
  // res.status(400);
  // return new Gson().toJson("Error controlador: " + e.getMessage());
  // }

  // };

  // public static Route deletePropiedad = (Request req, Response res) -> {
  // String id = req.queryParams("id");
  // String idReturned = servicioPropiedad.deletePropiedad(id);
  // return gson.toJson(idReturned);
  // };

}
