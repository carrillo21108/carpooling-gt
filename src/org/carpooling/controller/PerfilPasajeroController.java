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
import org.carpooling.controller.LoginController;

public class PerfilPasajeroController implements Initializable {
    
    private Principal escenarioPrincipal;
    
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtCorreo;
    @FXML private Button btnActualizar;
    LoginController login = new LoginController();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    
    public void getDatos(){
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BuscarPasajero( ? )}");
            procedimiento.setInt(1, login.getPasajeroActual().getCodigoPasajero());
            ResultSet resultado = procedimiento.executeQuery() ;
            
            txtNombres.setText(resultado.getString("nombres"));
            txtApellidos.setText(resultado.getString("apellidos"));
            txtCorreo.setText(resultado.getString("correo"));     
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void actualizar(){
         try{
             PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ActualizarPasajero(?,?,?,?,?,?,?,?,?)}");
             Pasajero registro = login.getPasajeroActual();
             registro.setNombre(txtNombres.getText());
             registro.setApellidos(txtApellidos.getText());
             registro.setCorreo(txtCorreo.getText());
             procedimiento.setString(1, registro.getNombre());
             procedimiento.setString(2, registro.getApellidos());
             procedimiento.setString(3, registro.getCorreo());
             procedimiento.setInt(4, registro.getCodigoConductor());
             procedimiento.setDouble(5, registro.getDeuda());
             procedimiento.setString(6, registro.getUsuario());
             procedimiento.setString(7, registro.getContrasenia());
             procedimiento.setString(8, registro.getUbicacion());
             procedimiento.setString(9, registro.getDestino());
             procedimiento.execute();
             limpiarControles();
              JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
             getDatos();
         }catch(Exception e){
             e.printStackTrace();
         }
    }
    
    
    public void limpiarControles(){
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
    }
 

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
}