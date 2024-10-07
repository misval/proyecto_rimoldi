package com.example.tp2spark;

public class Propiedad {
  private Integer id;
  private String ubicacion;
  private String tipo;
  private String destino;
  private Integer ambientes;
  private Integer banios;
  private Integer mts_cuadrados;
  
  public Propiedad(
    Integer id,
    String ubicacion,
    String tipo,
    String destino,
    Integer ambientes,
    Integer banios,
    Integer mts_cuadrados
  ) {
    this.id = id;
    this.ubicacion = ubicacion;
    this.tipo = tipo;
    this.destino = destino;
    this.ambientes = ambientes;
    this.banios = banios;
    this.mts_cuadrados = mts_cuadrados;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getUbicacion() {
    return ubicacion;
  }
  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }
  public String getTipoPropiedad() {
    return tipo;
  }
  public void setTipoPropiedad(String tipoPropiedad) {
    this.tipo = tipoPropiedad;
  }
  public String getDestinoPropiedad() {
    return destino;
  }
  public void setDestinoPropiedad(String destinoPropiedad) {
    this.destino = destinoPropiedad;
  }
  public Integer getAmbientes() {
    return ambientes;
  }
  public void setAmbientes(Integer ambientes) {
    this.ambientes = ambientes;
  }
  public Integer getBanios() {
    return banios;
  }
  public void setBanios(Integer banios) {
    this.banios = banios;
  }
  public Integer getMts_cuadrados() {
    return mts_cuadrados;
  }
  public void setMts_cuadrados(Integer mts_cuadrados) {
    this.mts_cuadrados = mts_cuadrados;
  }

  
}
