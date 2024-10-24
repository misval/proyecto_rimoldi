package com.example.tp2spark.models;

import lombok.Data;

@Data
public class Propiedad {
  private Propietario propietario;
  private Integer id;
  private String ubicacion;
  private String tipo;
  private String destino;
  private Integer ambientes;
  private Integer banios;
  private Integer mts_cuadrados;
  private String Propietario_PERSONA_CUIL;

  public Propiedad(Propietario propietario, Integer id, String ubicacion, String tipo, String destino,
      Integer ambientes, Integer banios, Integer mts_cuadrados, String Propietario_PERSONA_CUIL) {
    this.propietario = propietario;
    this.id = id;
    this.ubicacion = ubicacion;
    this.tipo = tipo;
    this.destino = destino;
    this.ambientes = ambientes;
    this.banios = banios;
    this.mts_cuadrados = mts_cuadrados;
    this.Propietario_PERSONA_CUIL = Propietario_PERSONA_CUIL;
  }

  public void addPropietario(Propietario propietario) {
    this.propietario = propietario;
  };
}
