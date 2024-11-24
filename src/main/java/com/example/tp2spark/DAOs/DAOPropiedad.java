package com.example.tp2spark.DAOs;

import java.util.List;

import com.example.tp2spark.Crud.CrudDAO;
import com.example.tp2spark.models.Propiedad;

public class DAOPropiedad extends CrudDAO<Propiedad> implements IDAOPropiedades {

  String tableName = "propiedades";
  String tablePK = "id";

  @Override
  public List<Propiedad> getAllPropiedades() {
    List<Propiedad> propiedades;
    try {
      propiedades = con
          .createQuery(
              "SELECT `id`, `ubicacion`, `tipo`, `destino`, `ambientes`, `banios`, `mts_cuadrados_cubiertos`,`descripcion`,`Propietario_PERSONA_CUIL` FROM propiedades;")
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
      return propiedad;
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }

  }

  public Integer getUltimoId() {
    Integer id = con.createQuery(
        "SELECT MAX(id) AS id FROM propiedades;")
        .executeAndFetchFirst(Integer.class);
    return id;
  }

  @Override
  public Class<Propiedad> getTClass() {
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

  @Override
  public Propiedad getPropiedadByContrato(int idContrato) {
    try {
      // tomo la propiedad solo con atributos de la base de datos
      Propiedad propiedad = con.createQuery(
          "SELECT ubicacion, tipo, destino FROM propiedades INNER JOIN contratos ON id = Propiedades_id AND IdContrato ="
              + idContrato + ";")
          .executeAndFetchFirst(Propiedad.class);
      return propiedad;
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }
  }
}
