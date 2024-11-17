package com.example.tp2spark.JSON;

public class ContratoJSON {
    public Garante garante;
    public Inquilino inquilino;
    public String fechaInicio;
    public String fechaFin;
    public String idPropiedad;

    public static class Garante {
        public String CUIL;
        public String DNI;
        public String nombre;
        public String email;
        public String fechaNacimiento;
        public double ingresos;
        public String trabajo;
        public String emailTrabajo;
    }

    public static class Inquilino {
        public String CUILInquilino;
        public String DNIInquilino;
        public String nombreInquilino;
        public String emailInquilino;
        public String fechaNacimientoInquilino;
        public char mascotas;
        public String empresaTrabajoInquilino;
        public int cantidadIntegrantes;
        public double ingresosInquilino;
    }
}
