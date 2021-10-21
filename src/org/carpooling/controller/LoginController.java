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
import org.carpooling.bean.Pasajero;
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
    private Pasajero pasajeroActual;
    
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
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_LoginPasajero(?,?)}");
            procedimiento.setString(1, txtUsuario.getText());
            procedimiento.setString(2, txtContrasenia.getText());
            ResultSet registro = procedimiento.executeQuery();
            int registros=0;
            while (registro.next()){
                registros++;
                if(registros>0){
                
                    pasajeroActual = new Pasajero(registro.getInt("codigoPasajero"), registro.getString("nombre"), registro.getString("apellidos"), registro.getString("correo"), registro.getInt("codigoConductor"), registro.getInt("deuda"), registro.getString("usuario"), registro.getString("contrasenia"), registro.getString("ubicacion"), registro.getString("destino"));
                    JOptionPane.showMessageDialog(null, "Bienvenido "+pasajeroActual.getNombre());
                    escenarioPrincipal.ventanaPerfil(); 

                    //PreparedStatement procedimiento2 = Conexion.getInstancia().getConexion().prepareCall("{call sp_BusquedaLogin(?,?)}");
                    //pasajeroActual = new Pasajero(int codigoPasajero, String nombre, String apellidos, String correo, int codigoConductor, int deuda, String usuario, String contrasenia, String ubicacion, String destino);
                }
          
            }
            if (registros == 0){
                JOptionPane.showMessageDialog(null,
                "Usuario o contrasenia incorrecta. ","Error",JOptionPane.ERROR_MESSAGE);
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
