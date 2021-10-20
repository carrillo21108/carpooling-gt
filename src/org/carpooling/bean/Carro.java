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
    private String nombre;

    public Carro(int codigoAuto, String marca, int modelo, String nombre) {
        this.codigoAuto = codigoAuto;
        this.marca = marca;
        this.modelo = modelo;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Carro{" + "codigoAuto=" + codigoAuto + ", marca=" + marca + ", modelo=" + modelo + ", nombre=" + nombre + '}';
    }
    
}