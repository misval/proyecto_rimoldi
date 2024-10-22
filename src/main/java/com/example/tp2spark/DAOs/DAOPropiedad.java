package com.example.tp2spark.DAOs;

import java.util.List;

import org.sql2o.Connection;

import com.example.tp2spark.DbConexion;
import com.example.tp2spark.models.Propiedad;
import com.example.tp2spark.models.Propietario;

public class DAOPropiedad implements IDAOPropiedades {
  private final Connection con = DbConexion.getSql2o().open();
  private static DAOPropietario servicioPropietario = new DAOPropietario();

  @Override
  public List<Propiedad> getAllPropiedades() {
    List<Propiedad> propiedades;
    try {
      propiedades = con
          .createQuery(
              "SELECT `id`, `ubicacion`, `tipo`, `destino`, `ambientes`, `banios`, `mts_cuadrados` FROM propiedades;")
          .executeAndFetch(Propiedad.class);
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }
    return propiedades;
  }

  @Override
  public Propiedad getPropiedadById(String id) {

    try {
      // tomo la propiedad solo con atributos de la base de datos
      Propiedad propiedad = con.createQuery(
          "SELECT `id`, `ubicacion`, `tipo`, `destino`, `ambientes`, `banios`, `mts_cuadrados` FROM PROPIEDADES WHERE ID = "
              + id + ";")
          .executeAndFetchFirst(Propiedad.class);
      // busco al propietario con id
      Propietario propietario = servicioPropietario.getPropietario((con.createQuery(
          "SELECT `Propietario_PERSONA_CUIL` FROM PROPIEDADES WHERE ID = "
              + id + ";"))
          .toString());

      // agrego propietario al bjeto
      propiedad.addPropietario(propietario);

      return propiedad;
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }

  }
}
