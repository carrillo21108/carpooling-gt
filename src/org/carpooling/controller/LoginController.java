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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.carpooling.bean.Conductor;
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
    @FXML private RadioButton radioPasajero;
    @FXML private RadioButton radioConductor;
    private String usuario;
    private String contrasenia;
    private Pasajero pasajeroActual;
    private Conductor conductorActual;
    
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
    
    public void togglePasajero(){
        if (radioConductor.isSelected()){
            radioConductor.setSelected(false);
        }
    }
     public void toggleConductor(){
        if (radioPasajero.isSelected()){
            radioPasajero.setSelected(false);
        }
    }
    
    public void validarUsuario(){
        try{
            PreparedStatement procedimiento3 = Conexion.getInstancia().getConexion().prepareCall("{call sp_DeleteToken()}");
            procedimiento3.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        
            try{
                String query = "";
                if (radioPasajero.isSelected()){
                    query = "{call sp_LoginPasajero(?,?)}";
                }
                else{
                    query = "{call sp_LoginConductor(?,?)}";

                }
                PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall(query);
                procedimiento.setString(1, txtUsuario.getText());
                procedimiento.setString(2, txtContrasenia.getText());
                ResultSet registro = procedimiento.executeQuery();
                int registros=0;
                while (registro.next()){
                    registros++;
                    if(registros!=0){
                        if (radioPasajero.isSelected()){
                            
                            JOptionPane.showMessageDialog(null, "Bienvenido "+registro.getString("nombre"));
                            PreparedStatement procedimiento2 = Conexion.getInstancia().getConexion().prepareCall("{call sp_SetToken(?,?)}");
                            procedimiento2.setInt(1, registro.getInt("codigoPasajero"));
                            procedimiento2.setString(2, "pasajero");
                            procedimiento2.execute();
                            escenarioPrincipal.ventanaMenuPasajero();
                        }
                        else{
                            
                            JOptionPane.showMessageDialog(null, "Bienvenido "+registro.getString("nombre"));
                            PreparedStatement procedimiento2 = Conexion.getInstancia().getConexion().prepareCall("{call sp_SetToken(?,?)}");
                            procedimiento2.setInt(1, registro.getInt("codigoConductor"));
                            procedimiento2.setString(2, "conductor");
                            procedimiento2.execute();
                            escenarioPrincipal.ventanaMenuConductor();
                        }   

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
    public Pasajero getPasajeroActual(){
        return pasajeroActual;
    }
    
    public void ventanaPerfilPasajero(){
        escenarioPrincipal.ventanaPerfilPasajero();
    }
    
    public void ventanaPerfilConductor(){
        escenarioPrincipal.ventanaPerfilConductor();
    }
    
}
