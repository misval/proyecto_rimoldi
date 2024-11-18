package com.example.tp2spark.DAOs;

import com.example.tp2spark.Crud.CrudDAO;
import com.example.tp2spark.models.Imagenes;

public class DAOImagenes extends CrudDAO implements IDAOImagenes {
    String tableName = "imagenes";
    String tablePK = "id";

    @Override
    public Class getTClass() {
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

}
