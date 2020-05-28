package com.example.puigincidencies.model;

public class Incidencia {
    public String uid;
    public String lugar;
    public String descripcion;
    public boolean aceptarIncidencia;
    public String mediaUrl;

    public Incidencia(){}

    public Incidencia(String uid, String lugar, String descripcion, boolean aceptarIncidencia, String mediaUrl) {
        this.uid = uid;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.aceptarIncidencia = aceptarIncidencia;
        this.mediaUrl = mediaUrl;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
