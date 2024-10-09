package com.example.tp2spark;

import org.sql2o.Connection;

import com.google.gson.Gson;

import spark.Route;
import spark.Request;
import spark.Response;

public class Controller {
  private static DAO servicio;
  private final static Gson gson = new Gson();

  static {
    try (Connection con = DbConexion.getSql2o().open()) {
        servicio = new DAO(con);
    } catch (Exception e) {
        System.err.println("Error initializing DAO: " + e.getMessage());
    }
}

  public static Route getPropiedades = (Request req, Response res) -> {
      try {
        res.status(200);
        return gson.toJson(servicio.getAllPropiedades());
      } catch (Exception e) {
        res.status(400);
        return new Gson().toJson("Error controlador: " + e.getMessage());
      }
  };

  public static Route getPropiedad = (Request req, Response res) ->{
    try {
      res.status(200);
      String id = req.params(":id");
      return gson.toJson(servicio.getPropiedadById(id));
    } catch (Exception e) {
      res.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
  };

  public static Route addPropiedad = (Request req, Response res) ->{
    Integer id = (int) (Math.random() * 100000);
    String ubicacion = req.queryParams("ubicacion");
    String tipoPropiedad = req.queryParams("tipo").toUpperCase();
    String destinoPropiedad = req.queryParams("destino").toUpperCase();
    Integer ambientes = Integer.valueOf(req.queryParams("ambientes"));
    Integer banios = Integer.valueOf(req.queryParams("banios"));
    Integer mts_cuadrados = Integer.valueOf(req.queryParams("mtsCuadrados"));
    
    Propiedad propiedad = new Propiedad(id, ubicacion, tipoPropiedad, destinoPropiedad, ambientes, banios, mts_cuadrados);

    try {
      res.status(200);
      return gson.toJson(servicio.addPropiedad(propiedad));
    } catch (Exception e) {
      res.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
  };

  public static Route updatePropiedad = (Request req, Response res) ->{
    Integer id = Integer.valueOf(req.queryParams("id"));
    String ubicacion = req.queryParams("ubicacion");
    String tipoPropiedad = req.queryParams("tipo").toUpperCase();
    String destinoPropiedad = req.queryParams("destino").toUpperCase();
    Integer ambientes = Integer.valueOf(req.queryParams("ambientes"));
    Integer banios = Integer.valueOf(req.queryParams("banios"));
    Integer mts_cuadrados = Integer.valueOf(req.queryParams("mtsCuadrados"));
    
    Propiedad propiedad = new Propiedad(id, ubicacion, tipoPropiedad, destinoPropiedad, ambientes, banios, mts_cuadrados);
    try {
      res.status(200);
      return gson.toJson(servicio.updatePropiedad(propiedad));
    } catch (Exception e) {
      res.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
    
  };

  public static Route deletePropiedad = (Request req, Response res) -> {
    String id = req.queryParams("id");
    String idReturned = servicio.deletePropiedad(id);
    return gson.toJson(idReturned);
  };

}
