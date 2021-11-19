/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.carpooling.controller;


import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;
import org.carpooling.sistema.Principal;
/**
 *
 * @author carlo
 */
public class MenuPasajeroController implements Initializable{
    
    private Principal escenarioPrincipal;
    
    @FXML private Button btnVer;
    @FXML private Button btnAbandonar;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
     public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void ventanaBuscarConductor(){
        this.escenarioPrincipal.ventanaBuscarConductor();
    }
    
    public void ventanaPerfilPasajero(){
        this.escenarioPrincipal.ventanaPerfilPasajero();
    }
    
    public void ventanaPagosPasajero(){
        this.escenarioPrincipal.ventanaPagoPasajero();
    }
    
}
