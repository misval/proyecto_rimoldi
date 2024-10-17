package com.example.tp2spark;

import java.util.List;

import org.sql2o.Connection;

public class DAOPropiedad implements DAOInterface {
  private final Connection con;

  public DAOPropiedad() {
    this.con = DbConexion.getSql2o().open();
  }

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
      return con.createQuery("SELECT * FROM PROPIEDADES WHERE ID = " + id + ";").executeAndFetchFirst(Propiedad.class);
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }

  }

  @Override
  public Propiedad updatePropiedad(Propiedad propiedad) {
    try {
      con.createQuery("UPDATE PROPIEDADES SET ubicacion='" + propiedad.getUbicacion() + "', TIPO='"
          + propiedad.getTipo() + "', DESTINO='" + propiedad.getDestino() + "', AMBIENTES="
          + propiedad.getAmbientes().toString() + ", BANIOS=" + propiedad.getBanios().toString() + ", MTS_CUADRADOS="
          + propiedad.getMts_cuadrados().toString() + " WHERE ID = " + propiedad.getId() + ";").executeUpdate();

    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }
    return propiedad;
  }

  @Override
  public boolean addPropiedad(Propiedad propiedad) {
    try {
      con.createQuery(
          "INSERT INTO propiedades (ID, UBICACION, TIPO, DESTINO, AMBIENTES, BANIOS, MTS_CUADRADOS) VALUES ("
              + propiedad.getId() + ", '" + propiedad.getUbicacion() + "', '" + propiedad.getTipo() + "', '"
              + propiedad.getDestino() + "', " + propiedad.getAmbientes() + ", " + propiedad.getBanios() + ", "
              + propiedad.getMts_cuadrados() + ");")
          .executeUpdate();
      return true;
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return false;
    }
  }

  @Override
  public String deletePropiedad(String id) {
    try {
      con.createQuery("DELETE FROM propiedades WHERE id = " + id + ";").executeUpdate();
      return id;
    } catch (Exception e) {
      System.err.println("Error al ejecutar la query: " + e.getMessage());
      return null;
    }
  }
}
