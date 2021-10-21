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
//Esta clase maneja la logica de la vista de login
public class LoginController implements Initializable {
    
    private Principal escenarioPrincipal;
    
    @FXML private Button btnRegistro;
    @FXML private Button btnIngresar;
    @FXML private TextField txtUsuario;
    @FXML private TextField txtContrasenia;
    private String usuario;
    private String contrasenia;
    
    
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
    
    public void validarUsuario(){
        usuario = txtUsuario.getText();
        contrasenia = txtContrasenia.getText();
        
    }
    public void ventanaPerfil(){
        escenarioPrincipal.ventanaPerfil();
    }
    
}
