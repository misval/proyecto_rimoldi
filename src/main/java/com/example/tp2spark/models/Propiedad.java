package com.example.tp2spark.models;

import lombok.Data;

@Data
public class Propiedad {
  private Integer id;
  private String ubicacion;
  private String tipo;
  private String destino;
  private Integer ambientes;
  private Integer banios;
  private Integer mts_cuadrados_cubiertos;
  private Integer mts_cuadrados_descubiertos;
  private Integer precio_venta;
  private Integer precio_alquiler;
  private Integer expensas;
  private String descripcion;
  private String Propietario_PERSONA_CUIL;

  public Propiedad(Integer id, String ubicacion, String tipo, String destino,
      Integer ambientes, Integer banios, Integer mts_cuadrados_cubiertos, Integer mts_cuadrados_descubiertos,
      Integer precio_venta, Integer precio_alquiler, Integer expensas,
      String descripcion, String Propietario_PERSONA_CUIL) {
    this.id = id;
    this.ubicacion = ubicacion;
    this.tipo = tipo;
    this.destino = destino;
    this.ambientes = ambientes;
    this.banios = banios;
    this.mts_cuadrados_cubiertos = mts_cuadrados_cubiertos;
    this.mts_cuadrados_descubiertos = mts_cuadrados_descubiertos;
    this.precio_venta = precio_venta;
    this.precio_alquiler = precio_alquiler;
    this.expensas = expensas;
    this.descripcion = descripcion;
    this.Propietario_PERSONA_CUIL = Propietario_PERSONA_CUIL;
  }
}
