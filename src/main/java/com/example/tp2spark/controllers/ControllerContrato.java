package com.example.tp2spark.controllers;

import com.example.tp2spark.DAOs.DAOContrato;
import com.example.tp2spark.DAOs.DAOGarante;
import com.example.tp2spark.DAOs.DAOInquilino;
import com.example.tp2spark.DAOs.DAOPropiedad;
import com.example.tp2spark.models.Contrato;
import com.example.tp2spark.models.Garante;
import com.example.tp2spark.models.Inquilino;
import com.example.tp2spark.models.Propiedad;
import com.google.gson.Gson;

import spark.Route;
import spark.Request;
import spark.Response;

public class ControllerContrato {
    private static DAOGarante servicioGarante = new DAOGarante();
    private static DAOInquilino servicioInquilino = new DAOInquilino();
    private static DAOPropiedad servicioPropiedad = new DAOPropiedad();
    private static DAOContrato servicioContrato = new DAOContrato();

    public static Route addContrato = (Request request, Response response) -> {

        String CUILInquilino = request.queryParams("cuilInquilino");
        String DNIInquilino = request.queryParams("dniInquilino");
        String nombreInquiliino = request.queryParams("nombreInquilino");
        String emailInquiliino = request.queryParams("emailInquilino");
        String fechaNacimientoInquiliino = request.queryParams("fechanacimientoInquilino");
        char mascotasInquiliino = request.queryParams("mascotas").charAt(0);
        String empresaTrabajoInquiliino = request.queryParams("trabajoInquilino");
        int cantidadIntegrantes = Integer.parseInt(request.queryParams("integrantes"));
        double ingresosInquilino = Double.parseDouble(request.queryParams("ingresos"));

        String CUIL = request.queryParams("cuil");
        String DNI = request.queryParams("dni");
        String nombre = request.queryParams("nombre");
        String email = request.queryParams("email");
        String fechaNacimiento = request.queryParams("fechanacimiento");
        double ingresos = Double.parseDouble(request.queryParams("ingresos"));
        String trabajo = request.queryParams("trabajo");
        String emailTrabajo = request.queryParams("emailTrabajo");

        String fechaInicio = request.queryParams("fechaInicio");
        String fechaFin = request.queryParams("fechaFin");
        String idPropiedad = request.queryParams("idprop");// deberia ser nombre, lo selecciona en la iltima pagina

        try {
            Garante garante1 = new Garante(CUIL, DNI, nombre, email, fechaNacimiento, ingresos, trabajo, emailTrabajo);
            Inquilino inquilino = new Inquilino(CUILInquilino, DNIInquilino, nombreInquiliino, emailInquiliino,
                    fechaNacimientoInquiliino, mascotasInquiliino, empresaTrabajoInquiliino, cantidadIntegrantes,
                    ingresosInquilino);
            Propiedad propiedad = servicioPropiedad.getPropiedadById(idPropiedad);

            Contrato contrato = new Contrato(fechaInicio, fechaFin);
            contrato.agregarPropiedad(propiedad);
            contrato.agregarGarante(garante1);
            contrato.agregarInquilino(inquilino);

            servicioGarante.addGarante(garante1);
            servicioInquilino.addInquilino(inquilino);

            return servicioContrato.addContrato(contrato);
        } catch (Exception e) {
            response.status(400);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };
}
