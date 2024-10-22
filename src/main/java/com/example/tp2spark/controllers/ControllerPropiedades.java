package com.example.tp2spark.controllers;

import com.example.tp2spark.DAOs.DAOPropiedad;
import com.example.tp2spark.models.Propiedad;
import com.google.gson.Gson;

import spark.Route;
import spark.Request;
import spark.Response;

public class ControllerPropiedades {
  private static DAOPropiedad servicioPropiedad = new DAOPropiedad();
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
      Propiedad propiedad = servicioPropiedad.getPropiedadById(id);
      if (propiedad == null) {
        throw new NullPointerException("Propiedad no encontrada");
      }
      return gson.toJson(propiedad);
    } catch (NullPointerException e) {
      res.status(404);
      return new Gson().toJson("Null pointer exception: " + e.getMessage());
    } catch (Exception e) {
      res.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
  };

}
