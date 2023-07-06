package com.example.comupnchuquirunaalvafinal.entities;

public class Carta {
    public int id;
    public int idDuelista;
    public String nombreC;
    public double puntosAtaque;
    public double puntosDefensa;
    public String urlImagen;
    double longuitud;
    double latitud;

    public Carta(){
    }

    public Carta(int id, int idDuelista, String nombreC, double puntosAtaque, double puntosDefensa, String urlImagen, double longuitud, double latitud) {
        this.id = id;
        this.idDuelista = idDuelista;
        this.nombreC = nombreC;
        this.puntosAtaque = puntosAtaque;
        this.puntosDefensa = puntosDefensa;
        this.urlImagen = urlImagen;
        this.longuitud = longuitud;
        this.latitud = latitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDuelista() {
        return idDuelista;
    }

    public void setIdDuelista(int idDuelista) {
        this.idDuelista = idDuelista;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public double getPuntosAtaque() {
        return puntosAtaque;
    }

    public void setPuntosAtaque(double puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    public double getPuntosDefensa() {
        return puntosDefensa;
    }

    public void setPuntosDefensa(double puntosDefensa) {
        this.puntosDefensa = puntosDefensa;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public double getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(double longuitud) {
        this.longuitud = longuitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
}
