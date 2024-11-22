package com.example.tp2spark.controllers;

import com.example.tp2spark.DAOs.DAOContrato;
import com.example.tp2spark.DAOs.DAOGarante;
import com.example.tp2spark.DAOs.DAOInquilino;
import com.example.tp2spark.DAOs.DAOPropiedad;
import com.example.tp2spark.models.Contrato;
import com.example.tp2spark.models.Garante;
import com.example.tp2spark.models.Inquilino;
import com.example.tp2spark.models.Propiedad;
import com.example.tp2spark.JSON.ContratoJSON;
import com.google.gson.Gson;

import spark.Route;
import spark.Request;
import spark.Response;

public class ControllerContrato {
    private static DAOGarante servicioGarante = new DAOGarante();
    private static DAOInquilino servicioInquilino = new DAOInquilino();
    private static DAOPropiedad servicioPropiedad = new DAOPropiedad();
    private static DAOContrato servicioContrato = new DAOContrato();
    private final static Gson gson = new Gson();

    public static Route getContrato = (Request request, Response response) -> {
        try {

            String CUIL = request.params(":CUIL");
            response.status(200);
            return gson.toJson(servicioContrato.getContrato(CUIL));
        } catch (NullPointerException e) {
            response.status(404);
            return new Gson().toJson("Null pointer exception: " + e.getMessage());
        } catch (Exception e) {
            response.status(400);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };

    public static Route addContrato = (Request request, Response response) -> {
        try {

            // Deserializa el JSON recibido en la clase ContratoRequest
            ContratoJSON contratoRequest = new Gson().fromJson(request.body(), ContratoJSON.class);

            // Crea las instancias necesarias
            Garante garante1 = new Garante(
                    contratoRequest.garante.CUIL,
                    contratoRequest.garante.DNI,
                    contratoRequest.garante.nombre,
                    contratoRequest.garante.email,
                    contratoRequest.garante.fechaNacimiento,
                    contratoRequest.garante.ingresos,
                    contratoRequest.garante.trabajo,
                    contratoRequest.garante.emailTrabajo);

            Inquilino inquilino = new Inquilino(
                    contratoRequest.inquilino.CUILInquilino,
                    contratoRequest.inquilino.DNIInquilino,
                    contratoRequest.inquilino.nombreInquilino,
                    contratoRequest.inquilino.emailInquilino,
                    contratoRequest.inquilino.fechaNacimientoInquilino,
                    contratoRequest.inquilino.mascotas,
                    contratoRequest.inquilino.empresaTrabajoInquilino,
                    contratoRequest.inquilino.cantidadIntegrantes,
                    contratoRequest.inquilino.ingresosInquilino);

            Propiedad propiedad = servicioPropiedad.getPropiedadById(contratoRequest.idPropiedad);

            Contrato contrato = new Contrato(contratoRequest.fechaInicio, contratoRequest.fechaFin);
            contrato.agregarPropiedad(propiedad);
            contrato.agregarGarante(garante1);
            contrato.agregarInquilino(inquilino);

            // Guarda los datos
            servicioGarante.addGarante(garante1);
            servicioInquilino.addInquilino(inquilino);

            response.status(201);
            return servicioContrato.addContrato(contrato);
        } catch (Exception e) {
            response.status(400);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };

}
