package com.example.tp2spark;

import java.util.List;

public interface PropiedadDAOInterface {
  List<Propiedad> getAllPropiedades();
  Propiedad getPropiedadById(String id);
  Propiedad addPropiedad(Propiedad propiedad);
  Propiedad updatePropiedad(Propiedad propiedad);
  String deletePropiedad(String id);
}
