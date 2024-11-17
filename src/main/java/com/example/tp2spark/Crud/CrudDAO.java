package com.example.tp2spark.Crud;

import java.lang.reflect.Field;

import org.sql2o.Connection;

import com.example.tp2spark.DbConexion;

/* 
* @param <T>
*/
public abstract class CrudDAO<T> {

    protected final Connection con = DbConexion.getSql2o().open();

    public abstract Class<T> getTClass();

    public abstract String getTablePK();

    public abstract String getTableName();

    public void insert(T t) throws Exception {
        Class<?> cls = t.getClass();
        Field[] fields = cls.getDeclaredFields();
        StringBuilder columnsInsertSQL = new StringBuilder("(");
        StringBuilder valuesInsertSQL = new StringBuilder("(");

        for (Field field : fields) {
            field.setAccessible(true); // Asegúrate de que puedes acceder al campo
            String name = field.getName();

            // Si el campo es `id` y se autogenera, lo omitimos
            if (name.equals("id")) {
                continue;
            }

            columnsInsertSQL.append(name).append(",");
            valuesInsertSQL.append(":").append(name).append(",");
        }

        // Eliminar la última coma
        columnsInsertSQL.setLength(columnsInsertSQL.length() - 1);
        valuesInsertSQL.setLength(valuesInsertSQL.length() - 1);

        columnsInsertSQL.append(")");
        valuesInsertSQL.append(")");

        String insertSQL = "INSERT INTO " + getTableName() + " " + columnsInsertSQL + " VALUES " + valuesInsertSQL;
        System.out.println(insertSQL);
        try {
            con.createQuery(insertSQL).bind(t).executeUpdate();
            System.out.println("Entidad insertada" + t);
        } catch (Exception e) {
            System.err.println("Error al ejecutar la query: " + e.getMessage());
            throw e; // Lanza el error para que el controlador lo maneje
        }
    }

}
