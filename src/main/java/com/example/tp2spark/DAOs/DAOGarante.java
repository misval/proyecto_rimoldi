package com.example.tp2spark.DAOs;

import java.util.List;

import org.sql2o.Connection;

import com.example.tp2spark.DbConexion;
import com.example.tp2spark.models.Garante;

public class DAOGarante implements IDAOGarante {

    private final Connection con = DbConexion.getSql2o().open();

    @Override
    public Garante getGarante(String CUILgarante) {
        try {
            return con.createQuery(
                    "SELECT CUIL, DNI, nombre, email,fechaNacimiento, ingresos, trabajo, emailTrabajo FROM PROPIETARIOS INNER JOIN PERSONAS ON GARANTES.GARANTE_PERSONA_CUIL = PERSONAS.CUIL WHERE PERSONA_CUIL = '"
                            + CUILgarante + "' ;")
                    .executeAndFetchFirst(Garante.class);
        } catch (Exception e) {
            System.err.println("Error al ejecutar la query: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addGarante(Garante garante) {
        try {
            con.createQuery("INSERT IGNORE INTO `PERSONAS` (CUIL, DNI, nombre, email, fechanacimiento) VALUES("
                    + "'" + garante.getCUIL() + "','" + garante.getDNI() + "','" + garante.getNombre() + "','"
                    + garante.getEmail() + "','" + garante.getFechaNacimiento() + "')")
                    .executeUpdate(); // Ejecutar la consulta

            con.createQuery("INSERT IGNORE INTO `GARANTES` (PERSONA_CUIL, ingresos, trabajo, emailTrabajo) VALUES('"
                    + garante.getCUIL() + "'," + garante.getIngresos() + ",'" + garante.getTrabajo() + "','"
                    + garante.getEmailTrabajo() + "')")
                    .executeUpdate(); // Ejecutar la consulta

            return true;
        } catch (Exception e) {
            System.err.println("Error al ejecutar la query: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Garante> getGaranteByContrato(int idContrato) {
        try {
            return con.createQuery(
                    "SELECT CUIL, DNI,nombre,fechanacimiento, ingresos, trabajo, emailTrabajo FROM personas INNER JOIN (garantes inner join contrato_has_garante ON Garante_Persona_CUIL = Persona_CUIL) ON CUIL = Garante_Persona_CUIL  WHERE Contrato_idContrato ="
                            + idContrato + ";")
                    .executeAndFetch(Garante.class);
        } catch (Exception e) {
            System.err.println("Error al ejecutar la query: " + e.getMessage());
            return null;
        }
    }

}
