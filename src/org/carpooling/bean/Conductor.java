/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.carpooling.bean;

/**
 *
 * @author Usuario
 */
public class Conductor {
    
    private int codigoConductor;
    private String nombre;
    private String apellidos;
    private String correo;
    private String usuario;
    private String contrasenia;
    private String ubicacion;
    private String destino;
    private int codigoAuto;
    private int espaciosDisponibles;

    public Conductor() {
    }

    public Conductor(int codigoConductor, String nombre, String apellidos, String correo, String usuario, String contrasenia, String ubicacion, String destino, int codigoAuto, int espaciosDisponibles) {
        this.codigoConductor = codigoConductor;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.ubicacion = ubicacion;
        this.destino = destino;
        this.codigoAuto = codigoAuto;
        this.espaciosDisponibles = espaciosDisponibles;
    }

    public int getCodigoConductor() {
        return codigoConductor;
    }

    public void setCodigoConductor(int codigoConductor) {
        this.codigoConductor = codigoConductor;
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

    public int getCodigoAuto() {
        return codigoAuto;
    }

    public void setCodigoAuto(int codigoAuto) {
        this.codigoAuto = codigoAuto;
    }

    public int getEspaciosDisponibles() {
        return espaciosDisponibles;
    }

    public void setEspaciosDisponibles(int espaciosDisponibles) {
        this.espaciosDisponibles = espaciosDisponibles;
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
        return "Conductor{" + "codigoConductor=" + codigoConductor + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", codigoAuto=" + codigoAuto + ", espaciosDisponibles=" + espaciosDisponibles + '}';
    }
    
    
}
