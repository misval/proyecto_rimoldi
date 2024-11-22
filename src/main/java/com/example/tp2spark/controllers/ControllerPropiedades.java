package com.example.tp2spark.controllers;

import java.util.List;

import com.example.tp2spark.DAOs.DAOImagenes;
import com.example.tp2spark.DAOs.DAOPropiedad;
import com.example.tp2spark.DAOs.DAOPropietario;
import com.example.tp2spark.models.Imagenes;
import com.example.tp2spark.models.Propiedad;
import com.example.tp2spark.models.Propietario;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import spark.Route;
import spark.Request;
import spark.Response;

public class ControllerPropiedades {
  private static DAOPropiedad servicioPropiedad = new DAOPropiedad();
  private static DAOPropietario servicioPropietario = new DAOPropietario();
  private static DAOImagenes servicioImagenes = new DAOImagenes();
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
      String id = req.params(":id");

      // Obtener propiedad y verificar si existe
      Propiedad propiedad = servicioPropiedad.getPropiedadById(id);
      if (propiedad == null) {
        throw new NullPointerException("Propiedad no encontrada");
      }

      // Obtener propietario asociado
      Propietario propietario = servicioPropietario.getPropietario(propiedad.getPropietario_PERSONA_CUIL());

      // Convertir propiedad y propietario a JsonObjects
      JsonObject propiedadJson = JsonParser.parseString(gson.toJson(propiedad)).getAsJsonObject();
      JsonObject propietarioJson = JsonParser.parseString(gson.toJson(propietario)).getAsJsonObject();

      // Agregar el propietario al JSON de la propiedad
      propiedadJson.add("Propietario", propietarioJson);

      // Obtener imágenes y procesarlas
      List<Imagenes> listaImagenes = servicioImagenes.getImagenes(id);
      JsonArray jsonArrayFotos = new JsonArray();

      for (Imagenes imagen : listaImagenes) {
        // Crear un nuevo JsonObject para cada imagen con solo el campo URL
        JsonObject imagenJson = new JsonObject();
        imagenJson.addProperty("url", imagen.getURL());
        jsonArrayFotos.add(imagenJson);
      }

      // Agregar las imágenes procesadas al JSON de la propiedad
      propiedadJson.add("Imagenes", jsonArrayFotos);

      // Retornar el JSON combinado como String
      res.status(200);
      return propiedadJson.toString();
    } catch (NullPointerException e) {
      res.status(404);
      return new Gson().toJson("Null pointer exception: " + e.getMessage());
    } catch (Exception e) {
      res.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
  };

  public static Route addPropiedad = (Request request, Response response) -> {
    JsonArray jsonArrayParsed = JsonParser.parseString(request.body()).getAsJsonArray();
    Propiedad propiedad = gson.fromJson(jsonArrayParsed.get(0), Propiedad.class);
    System.out.println(jsonArrayParsed.size());
    try {
      servicioPropiedad.insert(propiedad);

      Integer id = servicioPropiedad.getUltimoId();
      Imagenes imagen;
      for (int i = 1; i < jsonArrayParsed.size(); i++) {
        imagen = gson.fromJson(jsonArrayParsed.get(i), Imagenes.class);
        imagen.setIdPropiedad(id);
        servicioImagenes.insert(imagen);
      }
      response.type("application/json");
      response.status(201);
      return "Propiedad Ceada";
    } catch (Exception e) {
      response.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
  };

}
