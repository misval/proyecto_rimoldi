package com.example.tp2spark.DAOs;

import java.util.List;

import com.example.tp2spark.models.Propiedad;

public interface IDAOPropiedades {
  List<Propiedad> getAllPropiedades();

  Propiedad getPropiedadById(String id);

  Propiedad getPropiedadByContrato(int idContrato);

}
