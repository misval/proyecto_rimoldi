package com.example.tp2spark;

import org.sql2o.Sql2o;

public class DbConexion {
  protected static Sql2o sql2o;

  public static Sql2o getSql2o() {
    if(sql2o == null) {
      sql2o = new Sql2o("jdbc:mysql://localhost:3306/inmobiliaria", "root", "");
    }
    return sql2o;
  }
}
