package com.example.version1.adaptadores;

import java.io.Serializable;

public class ListElement implements Serializable {

    public Integer portada;
    public String nombre;
    public String empresa;
    public String presio;
    public String j;
    public String nombreusu;
    public String gmailcom;
    public String cedulacom;
    public String telefonocom;
    public String costo;
    public String el_limite;


    public ListElement(Integer portada, String nombre, String empresa, String presio, String j, String nombreusu, String gmailcom, String cedulacom, String telefonocom, String costo, String el_limite) {
        this.portada = portada;
        this.nombre = nombre;
        this.empresa = empresa;
        this.presio = presio;
        this.j = j;
        this.nombreusu = nombreusu;
        this.gmailcom = gmailcom;
        this.cedulacom = cedulacom;
        this.telefonocom = telefonocom;
        this.costo = costo;
        this.el_limite = el_limite;
    }


    public Integer getPortada() {
        return portada;
    }

    public void setPortada(Integer portada) {
        this.portada = portada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPresio() {
        return presio;
    }

    public void setPresio(String presio) {
        this.presio = presio;
    }

    public String getJ() {
        return j;
    }

    public void setJ(String j) {
        this.j = j;
    }

    public String getNombreusu() {
        return nombreusu;
    }

    public void setNombreusu(String nombreusu) {
        this.nombreusu = nombreusu;
    }

    public String getGmailcom() {
        return gmailcom;
    }

    public void setGmailcom(String gmailcom) {
        this.gmailcom = gmailcom;
    }

    public String getCedulacom() {
        return cedulacom;
    }

    public void setCedulacom(String cedulacom) {
        this.cedulacom = cedulacom;
    }

    public String getTelefonocom() {
        return telefonocom;
    }

    public void setTelefonocom(String telefonocom) {
        this.telefonocom = telefonocom;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getEl_limite() {
        return el_limite;
    }

    public void setEl_limite(String el_limite) {
        this.el_limite = el_limite;
    }
}
