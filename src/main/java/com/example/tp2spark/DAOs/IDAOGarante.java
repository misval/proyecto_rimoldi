package com.example.tp2spark.DAOs;

import com.example.tp2spark.models.Garante;

public interface IDAOGarante {
    public Garante getGarante(String CUILgarante);

    public boolean addGarante(Garante garante);
}
