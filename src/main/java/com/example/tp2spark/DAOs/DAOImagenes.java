package com.example.tp2spark.DAOs;

import java.util.List;

import com.example.tp2spark.Crud.CrudDAO;
import com.example.tp2spark.models.Imagenes;

public class DAOImagenes extends CrudDAO<Imagenes> implements IDAOImagenes {
    String tableName = "imagenes";
    String tablePK = "id";

    @Override
    public Class<Imagenes> getTClass() {
        return Imagenes.class;
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
    public List<Imagenes> getImagenes(String idPropietario) {
        try {
            List<Imagenes> imagenes = con.createQuery(
                    "SELECT * FROM `imagenes` WHERE idPropiedad=" + idPropietario + ";")
                    .executeAndFetch(Imagenes.class);
            return imagenes;

        } catch (Exception e) {
            System.err.println("Error al ejecutar la query: " + e.getMessage());
            return null;
        }
    }

}
