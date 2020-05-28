package com.example.puigincidencies.model;

public class IncidenciaRecyclerInicio {
    private String lugar;
    private String descripcion;
    private String id;

    public IncidenciaRecyclerInicio(){}

    public IncidenciaRecyclerInicio(String lugar, String descripcion, String id) {
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
