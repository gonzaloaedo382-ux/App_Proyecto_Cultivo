package com.example.cultivos_orquideas;

/* clase planta con sus atributos que debe tener en el resultadoplanta y en la libreria */
public class Planta {
    private String nombre;
    private String especie;
    private String luz;
    private String riego;
    private String estado;
    private int imagenResId;

    /* aqui se realiza un registro de cada planta detectada por la camara y verificar si hay coincidencias en la libreria */
    public Planta(String nombre, String especie, String luz, String riego, String estado, int imagenResId) {
        this.nombre = nombre;
        this.especie = especie;
        this.luz = luz;
        this.riego = riego;
        this.estado = estado;
        this.imagenResId = imagenResId;
    }
    // Getters y los datos que debe mostrar en el resultado detectado por la libreria
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public String getLuz() { return luz; }
    public String getRiego() { return riego; }
    public String getEstado() { return estado; }
    public int getImagenResId() { return imagenResId; }
}