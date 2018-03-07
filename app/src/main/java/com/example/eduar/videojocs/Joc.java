package com.example.eduar.videojocs;

import java.io.Serializable;

/**
 * Created by eduar on 26/02/2018.
 */

public class Joc implements Serializable {
    private int codi;
    private String nom, desenvolupador, genere, jugador, edat;


    public Joc(String nom, String desenvolupador, String genere, String jugador, String edat) {
        this.nom = nom;
        this.desenvolupador = desenvolupador;
        this.genere = genere;
        this.jugador = jugador;
        this.edat = edat;
    }

    public Joc(int id, String nom, String desenvolupador, String genere, String jugador, String edat) {
        this(nom, desenvolupador, genere, jugador, edat);
        setCodi(id);
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesenvolupador() {
        return desenvolupador;
    }

    public void setDesenvolupador(String desenvolupador) {
        this.desenvolupador = desenvolupador;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getEdat() {
        return edat;
    }

    public void setEdat(String edat) {
        this.edat = edat;
    }
}