package com.example.tp2spark;

import java.util.List;

import org.sql2o.Connection;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;

public class PropiedadControlador {
  private final PropiedadDAO servicio;
  private final Connection con;
  private final Gson gson = new Gson();

  public PropiedadControlador() {
    this.con = DbConexion.getSql2o().open();
    this.servicio = new PropiedadDAO(this.con);
  }

  public String getPropiedades(Request request, Response response) {
    List<Propiedad> res = servicio.getAllPropiedades();
    return gson.toJson(res);
  }

  public String getPropiedad(Request request, Response response) {
    String id = request.params(":id");
    Propiedad propiedad = servicio.getPropiedadById(id);
    return gson.toJson(propiedad);
  }

  public String addPropiedad(Request request, Response response) {
    Integer id = (int) (Math.random() * 100000);
    String ubicacion = request.queryParams("ubicacion");
    String tipoPropiedad = request.queryParams("tipo").toUpperCase();
    String destinoPropiedad = request.queryParams("destino").toUpperCase();
    Integer ambientes = Integer.valueOf(request.queryParams("ambientes"));
    Integer banios = Integer.valueOf(request.queryParams("banios"));
    Integer mts_cuadrados = Integer.valueOf(request.queryParams("mtsCuadrados"));
    
    Propiedad propiedad = new Propiedad(id, ubicacion, tipoPropiedad, destinoPropiedad, ambientes, banios, mts_cuadrados);
    
    Propiedad res = servicio.addPropiedad(propiedad);
    return gson.toJson(res);
  }

  public String updatePropiedad(Request request, Response response) {
    Integer id = Integer.valueOf(request.queryParams("id"));
    String ubicacion = request.queryParams("ubicacion");
    String tipoPropiedad = request.queryParams("tipo").toUpperCase();
    String destinoPropiedad = request.queryParams("destino").toUpperCase();
    Integer ambientes = Integer.valueOf(request.queryParams("ambientes"));
    Integer banios = Integer.valueOf(request.queryParams("banios"));
    Integer mts_cuadrados = Integer.valueOf(request.queryParams("mtsCuadrados"));
    
    Propiedad propiedad = new Propiedad(id, ubicacion, tipoPropiedad, destinoPropiedad, ambientes, banios, mts_cuadrados);
    
    Propiedad res = servicio.updatePropiedad(propiedad);
    return gson.toJson(res);
  }

  public String deletePropiedad(Request request, Response response) {
    String id = request.queryParams("id");
    String idReturned = servicio.deletePropiedad(id);
    return gson.toJson(idReturned);
  }

}
