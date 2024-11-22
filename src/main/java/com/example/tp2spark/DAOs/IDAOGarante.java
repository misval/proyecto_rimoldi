package com.example.tp2spark.DAOs;

import java.util.List;

import com.example.tp2spark.models.Garante;

public interface IDAOGarante {
    public Garante getGarante(String CUILgarante);

    public List<Garante> getGaranteByContrato(int idContrato);

    public boolean addGarante(Garante garante);
}
