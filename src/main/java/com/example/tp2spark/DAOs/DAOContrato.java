package com.example.tp2spark.DAOs;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;

import com.example.tp2spark.DbConexion;
import com.example.tp2spark.models.Contrato;
import com.example.tp2spark.models.Garante;
import com.example.tp2spark.models.Inquilino;
import com.example.tp2spark.models.Propiedad;

public class DAOContrato implements IDAOContrato {
        private final Connection con = DbConexion.getSql2o().open();

        @Override
        public boolean addContrato(Contrato contrato) {
                try {
                        con.createQuery(
                                        "INSERT IGNORE INTO `CONTRATOS` (fechaInicio, fechaFin, Inquilino_Persona_CUIL, Propiedades_id) VALUES("
                                                        + "'" + contrato.getFechaInicio() + "','"
                                                        + contrato.getFechaFin() + "','"
                                                        + contrato.getInquilino().getCUIL() + "','"
                                                        + contrato.getPropiedad().getId() + "')")
                                        .executeUpdate(); // Ejecutar la consulta
                        con.createQuery(
                                        "INSERT IGNORE INTO `Contrato_has_Garante`(Contrato_idContrato, Garante_Persona_CUIL) VALUES((SELECT MAX(idContrato) FROM CONTRATOS),"
                                                        + contrato.getGarantes().get(0).getCUIL() + ")")
                                        .executeUpdate();
                        return true;
                } catch (Exception e) {
                        System.err.println("Error al ejecutar la query: " + e.getMessage());
                        return false;
                }
        }

        @Override
        public List<Contrato> getContrato(String CUIL) {
                List<Contrato> contratos;

                try {
                        contratos = con.createQuery(
                                        "SELECT idContrato, fechaInicio, fechaFin, fechaCancelacion FROM contratos WHERE Inquilino_Persona_CUIL = '"
                                                        + CUIL + "';")
                                        .executeAndFetch(Contrato.class);

                        return contratos;
                } catch (Exception e) {
                        System.err.println("Error al ejecutar la query: " + e.getMessage());
                        return null;
                }
        }

}
