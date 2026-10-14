package com.example.cultivos_orquideas;

import java.util.Random;

public class Sensor_Humedad {
    private int humedad;
    private long timestamp;
    // Constructor
    public Sensor_Humedad() {
        this.humedad = 0;
        this.timestamp = System.currentTimeMillis();
    }
    public void simularLectura() {
        Random random = new Random();
        this.humedad = 40 + random.nextInt(30);
        this.timestamp = System.currentTimeMillis();
    }

    public void setLectura(int humedad) {
        this.humedad = humedad;
        this.timestamp = System.currentTimeMillis();
    }
    public int getHumedad() {
        return humedad;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public String mostrarLectura() {
        return "Humedad: " + humedad + "% | Fecha: " + timestamp;
    }
}
