package com.example.tp2spark;

import java.util.List;

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
      List<Propiedad> response = servicio.getAllPropiedades();
      return gson.toJson(response);
  };

  // public String getPropiedades(Request request, Response response) {
  //   List<Propiedad> res = servicio.getAllPropiedades();
  //   return gson.toJson(res);
  // }

  public static Route getPropiedad = (Request req, Response res) ->{
    String id = req.params(":id");
    Propiedad propiedad = servicio.getPropiedadById(id);
    return gson.toJson(propiedad);
  };

  // public String getPropiedad(Request request, Response response) {
  //   String id = request.params(":id");
  //   Propiedad propiedad = servicio.getPropiedadById(id);
  //   return gson.toJson(propiedad);
  // }

  public static Route addPropiedad = (Request req, Response res) ->{
    Integer id = (int) (Math.random() * 100000);
    String ubicacion = req.queryParams("ubicacion");
    String tipoPropiedad = req.queryParams("tipo").toUpperCase();
    String destinoPropiedad = req.queryParams("destino").toUpperCase();
    Integer ambientes = Integer.valueOf(req.queryParams("ambientes"));
    Integer banios = Integer.valueOf(req.queryParams("banios"));
    Integer mts_cuadrados = Integer.valueOf(req.queryParams("mtsCuadrados"));
    
    Propiedad propiedad = new Propiedad(id, ubicacion, tipoPropiedad, destinoPropiedad, ambientes, banios, mts_cuadrados);

    boolean response = false;

    try {
      response = servicio.addPropiedad(propiedad);
      res.status(200);
      return gson.toJson(response);
    } catch (Exception e) {
      res.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
  };
  
  // public String addPropiedad(Request request, Response response) {
  //   Integer id = (int) (Math.random() * 100000);
  //   String ubicacion = request.queryParams("ubicacion");
  //   String tipoPropiedad = request.queryParams("tipo").toUpperCase();
  //   String destinoPropiedad = request.queryParams("destino").toUpperCase();
  //   Integer ambientes = Integer.valueOf(request.queryParams("ambientes"));
  //   Integer banios = Integer.valueOf(request.queryParams("banios"));
  //   Integer mts_cuadrados = Integer.valueOf(request.queryParams("mtsCuadrados"));
    
  //   Propiedad propiedad = new Propiedad(id, ubicacion, tipoPropiedad, destinoPropiedad, ambientes, banios, mts_cuadrados);
    
  //   Propiedad res = servicio.addPropiedad(propiedad);
  //   return gson.toJson(res);
  // }

  public static Route updatePropiedad = (Request req, Response res) ->{
    Integer id = Integer.valueOf(req.queryParams("id"));
    String ubicacion = req.queryParams("ubicacion");
    String tipoPropiedad = req.queryParams("tipo").toUpperCase();
    String destinoPropiedad = req.queryParams("destino").toUpperCase();
    Integer ambientes = Integer.valueOf(req.queryParams("ambientes"));
    Integer banios = Integer.valueOf(req.queryParams("banios"));
    Integer mts_cuadrados = Integer.valueOf(req.queryParams("mtsCuadrados"));
    
    Propiedad propiedad = new Propiedad(id, ubicacion, tipoPropiedad, destinoPropiedad, ambientes, banios, mts_cuadrados);

    Propiedad response = servicio.updatePropiedad(propiedad);
    return gson.toJson(response);
  };

  // public String updatePropiedad(Request request, Response response) {
  //   Integer id = Integer.valueOf(request.queryParams("id"));
  //   String ubicacion = request.queryParams("ubicacion");
  //   String tipoPropiedad = request.queryParams("tipo").toUpperCase();
  //   String destinoPropiedad = request.queryParams("destino").toUpperCase();
  //   Integer ambientes = Integer.valueOf(request.queryParams("ambientes"));
  //   Integer banios = Integer.valueOf(request.queryParams("banios"));
  //   Integer mts_cuadrados = Integer.valueOf(request.queryParams("mtsCuadrados"));
    
  //   Propiedad propiedad = new Propiedad(id, ubicacion, tipoPropiedad, destinoPropiedad, ambientes, banios, mts_cuadrados);
    
  //   Propiedad res = servicio.updatePropiedad(propiedad);
  //   return gson.toJson(res);
  // }

  public static Route deletePropiedad = (Request req, Response res) -> {
    String id = req.queryParams("id");
    String idReturned = servicio.deletePropiedad(id);
    return gson.toJson(idReturned);
  };

  // public String deletePropiedad(Request request, Response response) {
  //   String id = request.queryParams("id");
  //   String idReturned = servicio.deletePropiedad(id);
  //   return gson.toJson(idReturned);
  // }

}
