/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.carpooling.bean.Conductor;
import org.carpooling.db.Conexion;
import org.carpooling.sistema.Principal;
import org.carpooling.controller.LoginController;


public class PerfilConductorController implements Initializable {
    
   private Principal escenarioPrincipal;
   
   @FXML private TextField txtNombres;
   @FXML private TextField txtApellidos;
   @FXML private TextField txtCorreo;
   @FXML private TextField txtModeloAuto;
   @FXML private TextField txtCantidadDeAsientos;
   @FXML private TextField txtUbicacion;
   @FXML private TextField txtDestino;
   @FXML private Button btnActualizar;
   private ResultSet token;
   private ResultSet conductorActual;
   private Conductor conductor = null;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try{
            PreparedStatement procedimientoToken = Conexion.getInstancia().getConexion().prepareCall("{call sp_GetToken()}");
            ResultSet token = procedimientoToken.executeQuery();
            int codigoUsuario = 0;
            while (token.next()){
                codigoUsuario = token.getInt("codigoUsuario");
            }

            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BuscarConductor(?)}");
            procedimiento.setInt(1, codigoUsuario);
            conductorActual = procedimiento.executeQuery();
            while (conductorActual.next()){      
                
                int codigoConductor = conductorActual.getInt("codigoConductor");
                String nombre = conductorActual.getString("nombre"); 
                String apellidos = conductorActual.getString("apellidos");
                String correo = conductorActual.getString("correo");
                String usuario = conductorActual.getString("usuario");
                String contrasenia = conductorActual.getString("contrasenia");
                String ubicacion = conductorActual.getString("ubicacion");
                String destino = conductorActual.getString("destino");
                int codigoAuto = conductorActual.getInt("codigoCarro");
                int espaciosDisponibles = conductorActual.getInt("espaciosDisponibles");

                conductor = new Conductor(codigoConductor, nombre, apellidos, correo, usuario, contrasenia, ubicacion, destino, codigoAuto, espaciosDisponibles);
                getDatos(conductor);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    public void getDatos(Conductor c){
        try{      
            txtNombres.setText(c.getNombre());
            txtApellidos.setText(c.getApellidos());
            txtCorreo.setText(c.getCorreo());
                
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void actualizar(){
         try{
             PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ActualizarConductor(?,?,?,?,?,?,?,?,?,?)}");
             

             conductor.setNombre(txtNombres.getText());
             conductor.setApellidos(txtApellidos.getText());
             conductor.setCorreo(txtCorreo.getText());
             procedimiento.setInt(1,conductor.getCodigoConductor());
             procedimiento.setString(2, conductor.getNombre());
             procedimiento.setString(3, conductor.getApellidos());
             procedimiento.setString(4, conductor.getCorreo());
             procedimiento.setString(5, conductor.getUsuario());
             procedimiento.setString(6, conductor.getContrasenia());
             procedimiento.setInt(7,conductor.getCodigoAuto());
             procedimiento.setInt(8,conductor.getEspaciosDisponibles());
             procedimiento.setString(9, conductor.getUbicacion());
             procedimiento.setString(10, conductor.getDestino());
             procedimiento.execute();
             limpiarControles();
             JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
             getDatos(conductor);
         }catch(Exception e){
             e.printStackTrace();
         }
    }
    
    
    public void limpiarControles(){
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
    }
    public void salir(){
        this.escenarioPrincipal.ventanaMenuConductor();
    }
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
}