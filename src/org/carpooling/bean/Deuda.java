/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.carpooling.bean;

/**
 *
 * @author carlo
 */
public class Deuda {
    
    private int codigoDeuda;
    private int codigoPasajero;
    private String ruta;
    private Double monto;
    private String nombreConductor;
    private int codigoConductor;

    public Deuda() {
    }

    public Deuda(int codigoDeuda, int codigoPasajero, String ruta, Double monto, String nombreConductor, int codigoConductor) {
        this.codigoDeuda = codigoDeuda;
        this.codigoPasajero = codigoPasajero;
        this.ruta = ruta;
        this.monto = monto;
        this.nombreConductor = nombreConductor;
        this.codigoConductor = codigoConductor;
    }

    public int getCodigoDeuda() {
        return codigoDeuda;
    }

    public void setCodigoDeuda(int codigoDeuda) {
        this.codigoDeuda = codigoDeuda;
    }

    public int getCodigoPasajero() {
        return codigoPasajero;
    }

    public void setCodigoPasajero(int codigoPasajero) {
        this.codigoPasajero = codigoPasajero;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    public int getCodigoConductor() {
        return codigoConductor;
    }

    public void setCodigoConductor(int codigoConductor) {
        this.codigoConductor = codigoConductor;
    }
    
    
    
}
