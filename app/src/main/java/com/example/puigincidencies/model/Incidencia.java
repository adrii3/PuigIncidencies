package com.example.puigincidencies.model;

public class Incidencia {

    public String uid;
    public String clase;
    public String descripcion;
    public String mediaUrl;

    public Incidencia(){}

    public Incidencia(String uid, String clase, String descripcion, String mediaUrl ){
        this.uid = uid;
        this.clase = clase;
        this.descripcion = descripcion;
        this.mediaUrl = mediaUrl;
    }
}
