package com.example.puigincidencies.model;

public class Incidencia {
    public String uid;
    public String lugar;
    public String descripcion;
    public boolean aceptarIncidencia;
    public boolean incidenciaSolucionada;
    public String uri;

    public Incidencia(){}

    public Incidencia(String uid,String lugar, String descripcion, boolean aceptarIncidencia, boolean incidenciaSolucionada, String uri) {
        this.uid = uid;
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.aceptarIncidencia = aceptarIncidencia;
        this.incidenciaSolucionada = incidenciaSolucionada;
        this.uri = uri;
    }



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isAceptarIncidencia() {
        return aceptarIncidencia;
    }

    public void setAceptarIncidencia(boolean aceptarIncidencia) {
        this.aceptarIncidencia = aceptarIncidencia;
    }

    public boolean isIncidenciaSolucionada() {
        return incidenciaSolucionada;
    }

    public void setIncidenciaSolucionada(boolean incidenciaSolucionada) {
        this.incidenciaSolucionada = incidenciaSolucionada;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
