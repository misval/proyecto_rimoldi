package com.example.tp2spark.DAOs;

import org.sql2o.Connection;

import com.example.tp2spark.DbConexion;
import com.example.tp2spark.models.Propietario;

public class DAOPropietario implements IDAOPropietario {
    private final Connection con = DbConexion.getSql2o().open();

    @Override
    public Propietario getPropietario(String idPropietario) {
        try {
            return con.createQuery(
                    "SELECT CUIL, DNI, nombre, email, fechanacimiento, CBU FROM PROPIETARIOS INNER JOIN PERSONAS ON PROPIETARIOS.PERSONA_CUIL = PERSONAS.CUIL WHERE PERSONA_CUIL = '"
                            + idPropietario + "' ;")
                    .executeAndFetchFirst(Propietario.class);
        } catch (Exception e) {
            System.err.println("Error al ejecutar la query: " + e.getMessage());
            return null;
        }

    }

}
