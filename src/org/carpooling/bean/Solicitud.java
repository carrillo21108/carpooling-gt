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
    private int codigoConductor;

    public Solicitud(int codigoSolicitud, int codigoPasajero, String nombre, String apellidos, int codigoConductor) {
        this.codigoSolicitud = codigoSolicitud;
        this.codigoPasajero = codigoPasajero;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.codigoConductor = codigoConductor;
    }

    public Solicitud() {
    }
}
