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
public class Carro {
    
    private int codigoAuto;
    private String marca;
    private int modelo;
    private int cantAsientos;

    public Carro(int codigoAuto, String marca, int modelo, int cantAsientos) {
        this.codigoAuto = codigoAuto;
        this.marca = marca;
        this.modelo = modelo;
        this.cantAsientos = cantAsientos;
    }

    public Carro() {
    }

    public int getCodigoAuto() {
        return codigoAuto;
    }

    public void setCodigoAuto(int codigoAuto) {
        this.codigoAuto = codigoAuto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public int getCantAsientos() {
        return cantAsientos;
    }

    public void setCantAsientos(int cantAsientos) {
        this.cantAsientos = cantAsientos;
    }

    @Override
    public String toString() {
        return "Carro{" + "codigoAuto=" + codigoAuto + ", marca=" + marca + ", modelo=" + modelo + ", cantAsientos=" + cantAsientos + '}';
    }
    
}