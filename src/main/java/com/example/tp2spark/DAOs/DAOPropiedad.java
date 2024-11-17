package com.example.tp2spark.DAOs;

import java.util.List;

import org.sql2o.Connection;

import com.example.tp2spark.DbConexion;
import com.example.tp2spark.Crud.CrudDAO;
import com.example.tp2spark.models.Propiedad;
import com.example.tp2spark.models.Propietario;

public class DAOPropiedad extends CrudDAO implements IDAOPropiedades {

  private static DAOPropietario servicioPropietario = new DAOPropietario();

  String tableName = "propiedades";
  String tablePK = "id";

  @Override
  public List<Propiedad> getAllPropiedades() {
    List<Propiedad> propiedades;
    try {
      propiedades = con
          .createQuery(
              "SELECT `id`, `ubicacion`, `tipo`, `destino`, `ambientes`, `banios`, `mts_cuadrados_cubiertos` FROM propiedades;")
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
          "SELECT `id`, `ubicacion`, `tipo`, `destino`, `ambientes`, `banios`, `mts_cuadrados_cubiertos`,`Propietario_PERSONA_CUIL` FROM PROPIEDADES WHERE ID = "
              + id + ";")
          .executeAndFetchFirst(Propiedad.class);
      // busco al propietario con id
      Propietario propietario = servicioPropietario.getPropietario(propiedad.getPropietario_PERSONA_CUIL());

      // agrego propietario al bjeto

      return propiedad;
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }

  }

  @Override
  public Class getTClass() {
    return Propiedad.class;
  }

  @Override
  public String getTablePK() {
    return tablePK;
  }

  @Override
  public String getTableName() {
    return tableName;
  }
}
