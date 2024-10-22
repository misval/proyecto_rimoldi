package com.example.tp2spark.DAOs;

import org.sql2o.Connection;

import com.example.tp2spark.DbConexion;
import com.example.tp2spark.models.Inquilino;

public class DAOInquilino implements IDAOInquilino {

    private final Connection con = DbConexion.getSql2o().open();

    @Override
    public boolean addInquilino(Inquilino inquilino) {
        try {
            con.createQuery("INSERT IGNORE INTO `PERSONAS` (CUIL, DNI, nombre, email, fechanacimiento) VALUES("
                    + "'" + inquilino.getCUIL() + "','" + inquilino.getDNI() + "','" + inquilino.getNombre() + "','"
                    + inquilino.getEmail() + "','" + inquilino.getFechaNacimiento() + "')")
                    .executeUpdate(); // Ejecutar la consulta

            con.createQuery(
                    "INSERT IGNORE INTO `inquilinoS` (PERSONA_CUIL, ingresos, empresaTrabajo, cantidadIntegrantes, mascotas) VALUES('"
                            + inquilino.getCUIL() + "'," + inquilino.getIngresos() + ",'"
                            + inquilino.getEmpresaTrabajo() + "',"
                            + inquilino.getCantidadIntegrantes() + ",'" + inquilino.getMascotas() + "')")
                    .executeUpdate(); // Ejecutar la consulta

            return true;
        } catch (Exception e) {
            System.err.println("Error al ejecutar la query: " + e.getMessage());
            return false;
        }
    }

}
