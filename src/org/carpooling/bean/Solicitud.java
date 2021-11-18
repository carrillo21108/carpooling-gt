/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.carpooling.bean;


public class Solicitud {
    private int codigoSolicitud;
    private int codigoPasajero;
    private String nombre;
    private String apellidos;
    private String ubicacion;
    private int codigoConductor;

    public Solicitud(int codigoSolicitud, int codigoPasajero, String nombre, String apellidos, String ubicacion, int codigoConductor) {
        this.codigoSolicitud = codigoSolicitud;
        this.codigoPasajero = codigoPasajero;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ubicacion = ubicacion;
        this.codigoConductor = codigoConductor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Solicitud() {
    }

    public int getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(int codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public int getCodigoPasajero() {
        return codigoPasajero;
    }

    public void setCodigoPasajero(int codigoPasajero) {
        this.codigoPasajero = codigoPasajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCodigoConductor() {
        return codigoConductor;
    }

    public void setCodigoConductor(int codigoConductor) {
        this.codigoConductor = codigoConductor;
    }
}
