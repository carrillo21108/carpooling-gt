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
public class Pasajero {
    
    private int codigoPasajero;
    private String nombre;
    private String apellidos;
    private String correo;
    private int codigoConductor;
    private int deuda;
    private String usuario;
    private String contrasenia;
    private String ubicacion;
    private String destino;

    public Pasajero() {
    }
    
    public Pasajero(int codigoPasajero, String nombre, String apellidos, String correo, int codigoConductor, int deuda, String usuario, String contrasenia, String ubicacion, String destino) {
        this.codigoPasajero = codigoPasajero;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.codigoConductor = codigoConductor;
        this.deuda = deuda;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.ubicacion = ubicacion;
        this.destino = destino;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCodigoConductor() {
        return codigoConductor;
    }

    public void setCodigoConductor(int codigoConductor) {
        this.codigoConductor = codigoConductor;
    }

    public int getDeuda() {
        return deuda;
    }

    public void setDeuda(int deuda) {
        this.deuda = deuda;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Pasajero{" + "codigoPasajero=" + codigoPasajero + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo + ", codigoConductor=" + codigoConductor + ", deuda=" + deuda + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", ubicacion=" + ubicacion + ", destino=" + destino + '}';
    }
    
    
    
}
