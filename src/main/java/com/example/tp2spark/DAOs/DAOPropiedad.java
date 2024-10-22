package com.example.tp2spark.DAOs;

import java.util.List;

import org.sql2o.Connection;

import com.example.tp2spark.DbConexion;
import com.example.tp2spark.models.Propiedad;

public class DAOPropiedad implements IDAOPropiedades {
  private final Connection con = DbConexion.getSql2o().open();

  @Override
  public List<Propiedad> getAllPropiedades() {
    List<Propiedad> propiedades;
    try {
      propiedades = con.createQuery("SELECT * FROM propiedades;").executeAndFetch(Propiedad.class);
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }
    return propiedades;
  }

  @Override
  public Propiedad getPropiedadById(String id) {

    try {
      return con.createQuery(
          "SELECT `id`, `ubicacion`, `tipo`, `destino`, `ambientes`, `banios`, `mts_cuadrados` FROM PROPIEDADES WHERE ID = "
              + id + ";")
          .executeAndFetchFirst(Propiedad.class);
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }

  }
}
