package com.example.tp2spark.DAOs;

import java.util.List;

import com.example.tp2spark.models.Imagenes;

public interface IDAOImagenes {
    public List<Imagenes> getImagenes(String idPropietario);
}
