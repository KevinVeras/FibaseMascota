package com.example.fibasemascota;

public class MainModel {

    String edad,nombre,tipo,tamanio;
    String vDistemper,vParvovirus,vRabia;
    String tmini,tmediano,tgrande,tgigante;

    MainModel(){

    }


    public MainModel(String edad, String nombre, String tipo, String tamanio, String vDistemper,
                     String vParvovirus, String vRabia, String tmini, String tmediano, String tgrande, String tgigante) {
        this.edad = edad;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.vDistemper = vDistemper;
        this.vParvovirus = vParvovirus;
        this.vRabia = vRabia;
        this.tmini = tmini;
        this.tmediano = tmediano;
        this.tgrande = tgrande;
        this.tgigante = tgigante;
    }

    public String getTmini() {
        return tmini;
    }

    public void setTmini(String tmini) {
        this.tmini = tmini;
    }

    public String getTmediano() {
        return tmediano;
    }

    public void setTmediano(String tmediano) {
        this.tmediano = tmediano;
    }

    public String getTgrande() {
        return tgrande;
    }

    public void setTgrande(String tgrande) {
        this.tgrande = tgrande;
    }

    public String getTgigante() {
        return tgigante;
    }

    public void setTgigante(String tgigante) {
        this.tgigante = tgigante;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getvDistemper() {
        return vDistemper;
    }

    public void setvDistemper(String vDistemper) {
        this.vDistemper = vDistemper;
    }

    public String getvParvovirus() {
        return vParvovirus;
    }

    public void setvParvovirus(String vParvovirus) {
        this.vParvovirus = vParvovirus;
    }

    public String getvRabia() {
        return vRabia;
    }

    public void setvRabia(String vRabia) {
        this.vRabia = vRabia;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
