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
    @FXML private TextField txtContrasenia;
    @FXML private TextField txtRepetirContrasenia;
    @FXML private Button btnActualizar;
    private ResultSet token;
    private ResultSet pasajeroActual;
    private Pasajero pasajero = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            PreparedStatement procedimientoToken = Conexion.getInstancia().getConexion().prepareCall("{call sp_GetToken()}");
            ResultSet token = procedimientoToken.executeQuery();
            int codigoUsuario = 0;
            while (token.next()){
                codigoUsuario = token.getInt("codigoUsuario");
            }

            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BuscarPasajero(?)}");
            procedimiento.setInt(1, codigoUsuario);
            pasajeroActual = procedimiento.executeQuery();
            while (pasajeroActual.next()){
                     int codigoPasajero = pasajeroActual.getInt("codigoPasajero");
                     String nombre = pasajeroActual.getString("nombre"); 
                     String apellidos = pasajeroActual.getString("apellidos");
                     String correo = pasajeroActual.getString("correo");
                     int codigoConductor = pasajeroActual.getInt("codigoConductor");
                     int deuda =  pasajeroActual.getInt("deuda") ;
                     String usuario = pasajeroActual.getString("usuario");
                     String contrasenia = pasajeroActual.getString("contrasenia");
                     String ubicacion = pasajeroActual.getString("ubicacion");
                     String destino = pasajeroActual.getString("destino");
                     pasajero = new Pasajero(codigoPasajero, nombre,  apellidos,  correo, codigoConductor,  deuda, usuario,  contrasenia, ubicacion, destino);
                    getDatos(pasajero);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

       
    }
    
    
    public void salir(){
        this.escenarioPrincipal.ventanaMenuPasajero();
    }
    
    public void getDatos(Pasajero p){
        try{      
            txtNombres.setText(p.getNombre());
            txtApellidos.setText(p.getApellidos());
            txtCorreo.setText(p.getCorreo()); 
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void actualizar(){
         try{
             PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ActualizarPasajero(?,?,?,?,?,?,?,?,?)}");
             

             pasajero.setNombre(txtNombres.getText());
             pasajero.setApellidos(txtApellidos.getText());
             pasajero.setCorreo(txtCorreo.getText());
             pasajero.setContrasenia(txtContrasenia.getText());
             procedimiento.setInt(1,pasajero.getCodigoPasajero());
             procedimiento.setString(2, pasajero.getNombre());
             procedimiento.setString(3, pasajero.getApellidos());
             procedimiento.setString(4, pasajero.getCorreo());
             procedimiento.setDouble(6, pasajero.getDeuda());
             procedimiento.setString(7, pasajero.getUsuario());
             procedimiento.setString(8, pasajero.getContrasenia());
             procedimiento.setString(9, pasajero.getUbicacion());
             procedimiento.setString(10, pasajero.getDestino());
             
             if(txtContrasenia.getText().equals(txtRepetirContrasenia.getText())){
                procedimiento.execute();
             }else{
                 JOptionPane.showMessageDialog(null, "Las contrase??as no coinciden!, Intente de nuevo.");
                 txtContrasenia.setText("");
                 txtRepetirContrasenia.setText("");
             }
         
             limpiarControles();
              JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
             getDatos(pasajero);
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