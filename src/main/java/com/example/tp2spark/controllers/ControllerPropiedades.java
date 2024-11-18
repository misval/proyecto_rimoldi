package com.example.tp2spark.controllers;

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
      res.status(200);
      String id = req.params(":id");
      Propiedad propiedad = servicioPropiedad.getPropiedadById(id);
      if (propiedad == null) {
        throw new NullPointerException("Propiedad no encontrada");
      }
      Propietario propietario = servicioPropietario.getPropietario(propiedad.getPropietario_PERSONA_CUIL());
      // System.out.println(gson.toJson(propietario));
      // return gson.toJson(propiedad);
      // Crear un objeto JSON para la propiedad
      JsonObject propiedadJson = JsonParser.parseString(gson.toJson(propiedad)).getAsJsonObject();

      // Crear un objeto JSON para el propietario
      JsonObject propietarioJson = JsonParser.parseString(gson.toJson(propietario)).getAsJsonObject();

      // Insertar el objeto propietario como un nuevo atributo en la propiedad
      propiedadJson.add("Propietario", propietarioJson);

      // Retornar el JSON combinado como String
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

      // Crear una entidad de tipo Imagenes (segundo elemento del array)
      Imagenes imagen = gson.fromJson(jsonArrayParsed.get(1), Imagenes.class);
      // Asignar el id de la propiedad a la entidad Imagenes
      imagen.setIdPropiedad(id);
      System.out.println(imagen);
      // Insertar la imagen en la base de datos
      servicioImagenes.insert(imagen); // Suponiendo que tienes un DAO para `Imagenes`

      // Responder con un mensaje de éxito

      response.type("application/json");
      response.status(201);
      return "Propiedad Ceada";
    } catch (Exception e) {
      response.status(400);
      return new Gson().toJson("Error controlador: " + e.getMessage());
    }
  };

}
