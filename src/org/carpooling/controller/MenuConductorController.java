/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.carpooling.controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import org.carpooling.sistema.Principal;
/**
 *
 * @author Usuario
 */
public class MenuConductorController implements Initializable {
    
    @FXML private Button cancelar;
    @FXML private MenuItem  verGrupoItem;
    private Principal escenarioPrincipal;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }   
    
        
    public void verGrupo() {
        escenarioPrincipal.ventanaGrupoConductor();
    }
    
    public void verSolicitudes() {
        escenarioPrincipal.ventanaSolicitudesConductor();
    }
    
    public void verPerfil(){
        escenarioPrincipal.ventanaPerfilConductor();
    }   
    
    public void verPagos(){
        escenarioPrincipal.ventanaPagoConductor();
    }
    
    public void verPuntos(){
        escenarioPrincipal.ventanaPuntosPorRecorrer();
    }
    
    public void salir(){
        escenarioPrincipal.login();
    }

    public void obtenerPasajeros(){
        
    
    }
}
