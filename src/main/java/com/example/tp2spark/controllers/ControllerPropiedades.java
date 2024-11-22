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

  public static Route getPropiedades=(Request req,Response res)->{try{res.status(200);return gson.toJson(servicioPropiedad.getAllPropiedades());}catch(
  Exception e)
  {
    res.status(400);
    return new Gson().toJson("Error controlador: " + e.getMessage());
  }};

  public static Route getPropiedad = (Request req, Response res) -> {
    try {

      String id = req.params(":id");

      Propiedad propiedad = servicioPropiedad.getPropiedadById(id);
      if (propiedad == null) {
        throw new NullPointerException("Propiedad no encontrada");
      }
      Propietario propietario = servicioPropietario.getPropietario(propiedad.getPropietario_PERSONA_CUIL());
      
      // Crear un objeto JSON para la propiedad y propietario
      JsonObject propiedadJson = JsonParser.parseString(gson.toJson(propiedad)).getAsJsonObject();
      JsonObject propietarioJson = JsonParser.parseString(gson.toJson(propietario)).getAsJsonObject();

      // Insertar el objeto propietario como un nuevo atributo en la propiedad
      propiedadJson.add("Propietario", propietarioJson);
      
      JsonArray jsonArrayFotos = JsonParser.parseString(gson.toJson(servicioImagenes.getImagenes(id).get(0)))

      for (
  int i = 0;i<jsonArrayFotos.size();i++)
  {
    JsonObject imagenJson = jsonArrayFotos.get(i);
    imagenJson.remove("idPropiedad");
    propiedadJson.add("Imagen", imagenJson);
  }

  // Retornar el JSON combinado como String
  res.status(200);return propiedadJson.toString();}catch(
  NullPointerException e)
  {
    res.status(404);
    return new Gson().toJson("Null pointer exception: " + e.getMessage());
  }catch(
  Exception e)
  {
    res.status(400);
    return new Gson().toJson("Error controlador: " + e.getMessage());
  }};

  public static Route addPropiedad=(Request request,Response response)->{JsonArray jsonArrayParsed=JsonParser.parseString(request.body()).getAsJsonArray();Propiedad propiedad=gson.fromJson(jsonArrayParsed.get(0),Propiedad.class);System.out.println(jsonArrayParsed.size());try{servicioPropiedad.insert(propiedad);

  Integer id=servicioPropiedad.getUltimoId();Imagenes imagen;for(int i=1;i<jsonArrayParsed.size();i++){imagen=gson.fromJson(jsonArrayParsed.get(i),Imagenes.class);
  // Asignar el id de la propiedad a la entidad Imagenes
  imagen.setIdPropiedad(id);System.out.println(imagen);
  // Insertar la imagen en la base de datos
  servicioImagenes.insert(imagen);}response.type("application/json");response.status(201);return"Propiedad Ceada";}catch(
  Exception e)
  {
    response.status(400);
    return new Gson().toJson("Error controlador: " + e.getMessage());
  }
};

}
