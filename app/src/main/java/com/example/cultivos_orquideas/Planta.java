package com.example.cultivos_orquideas;

public class Planta {
    private String nombre;
    private String especie;
    private String luz;
    private String riego;
    private String estado;
    private int imagenResId; // ID del recurso drawable de la imagen

    public Planta(String nombre, String especie, String luz, String riego, String estado, int imagenResId) {
        this.nombre = nombre;
        this.especie = especie;
        this.luz = luz;
        this.riego = riego;
        this.estado = estado;
        this.imagenResId = imagenResId;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public String getLuz() { return luz; }
    public String getRiego() { return riego; }
    public String getEstado() { return estado; }
    public int getImagenResId() { return imagenResId; }
}