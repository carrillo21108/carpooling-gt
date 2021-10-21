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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.carpooling.sistema.Principal;

/**
 *
 * @author carlo
 */
public class PerfilConductorController implements Initializable {
    
   private Principal escenarioPrincipal;
   
   @FXML private TextField txtNombre;
   @FXML private TextField txtApellidos;
   @FXML private TextField txtCorreo;
   @FXML private TextField txtModeloAuto;
   @FXML private TextField txtCantidadDeAsientos;
   @FXML private TextField txtUbicacion;
   @FXML private TextField txtDestino;
   @FXML private Button btnActualizar;
   
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
}
