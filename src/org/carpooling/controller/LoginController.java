package org.carpooling.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.carpooling.db.Conexion;
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
       
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BusquedaLogin(?,?)}");
            procedimiento.setString(1, txtUsuario.getText());
            procedimiento.setString(2, txtContrasenia.getText());
            ResultSet registro = procedimiento.executeQuery();
            int n=0;
            while (registro.next()){
                n++;
            }
            if (n>0){
                JOptionPane.showMessageDialog(null, String.valueOf(n));
                
                escenarioPrincipal.ventanaPerfil();
            }
            else{
                System.out.println(String.valueOf(n));
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
                txtUsuario.setText("");
                txtContrasenia.setText("");
            }
          
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void ventanaPerfil(){
        escenarioPrincipal.ventanaPerfil();
    }
    
}
