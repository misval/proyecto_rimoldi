package com.example.tp2spark.DAOs;

import java.util.List;

import com.example.tp2spark.models.Contrato;

public interface IDAOContrato {
    public boolean addContrato(Contrato contrato);

    public List<Contrato> getContrato(String CUIL);

    public Contrato getContratoById(int idContrato);
}
