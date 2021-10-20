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
//Esta clase maneja la logica de la vista de login
public class LoginController implements Initializable {
    
    private Principal escenarioPrincipal;
    
    @FXML private Button btnRegistro;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void ventanaRegistro(){
        escenarioPrincipal.ventanaRegistro();
    }
    
    public void ventanaPerfil(){
        escenarioPrincipal.ventanaPerfil();
    }
    
}
