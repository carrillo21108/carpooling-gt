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
    private ResultSet token;
    private ResultSet pasajeroActual;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
        PreparedStatement procedimientoToken = Conexion.getInstancia().getConexion().prepareCall("{call sp_GetToken()}");
        token = procedimientoToken.executeQuery() ;
        PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BuscarPasajero( ? )}");
        procedimiento.setInt(1, token.getInt("codigoUsuario"));
        pasajeroActual = procedimiento.executeQuery() ;
        }catch(Exception e){
            e.printStackTrace();
        }

        getDatos();
    }
    
    
    
    
    public void getDatos(){
        try{      
            txtNombres.setText(pasajeroActual.getString("nombres"));
            txtApellidos.setText(pasajeroActual.getString("apellidos"));
            txtCorreo.setText(pasajeroActual.getString("correo"));     
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void actualizar(){
         try{
             PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ActualizarPasajero(?,?,?,?,?,?,?,?,?)}");
             Pasajero pasajero = new Pasajero(pasajeroActual.getInt("codigoPasajero"), pasajeroActual.getString("nombre"), pasajeroActual.getString("apellidos"), pasajeroActual.getString("correo"), pasajeroActual.getInt("codigoConductor"), pasajeroActual.getInt("deuda"), pasajeroActual.getString("usuario"), pasajeroActual.getString("contrasenia"), pasajeroActual.getString("ubicacion"), pasajeroActual.getString("destino"));

             pasajero.setNombre(txtNombres.getText());
             pasajero.setApellidos(txtApellidos.getText());
             pasajero.setCorreo(txtCorreo.getText());
             procedimiento.setString(1, pasajero.getNombre());
             procedimiento.setString(2, pasajero.getApellidos());
             procedimiento.setString(3, pasajero.getCorreo());
             procedimiento.setInt(4, pasajero.getCodigoConductor());
             procedimiento.setDouble(5, pasajero.getDeuda());
             procedimiento.setString(6, pasajero.getUsuario());
             procedimiento.setString(7, pasajero.getContrasenia());
             procedimiento.setString(8, pasajero.getUbicacion());
             procedimiento.setString(9, pasajero.getDestino());
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