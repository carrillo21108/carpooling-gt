package org.carpooling.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.carpooling.bean.Carro;
import org.carpooling.bean.Conductor;
import org.carpooling.bean.Pasajero;
import org.carpooling.db.Conexion;
import org.carpooling.sistema.Principal;

public class RegistroController implements Initializable {
    
    private Principal escenarioPrincipal;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtContrasenia;
    @FXML private TextField txtUbicacion;
    @FXML private TextField txtDestino;
    @FXML private TextField txtUsuario;
    @FXML private TextField txtModelo;
    @FXML private TextField txtMarca;
    @FXML private RadioButton radioPasajero;
    @FXML private RadioButton radioConductor;
    @FXML private RadioButton radioMasculino;
    @FXML private RadioButton radioFemenino;
    @FXML private Button btnRegistrarse;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void togglePasajero(){
        if (radioConductor.isSelected()){
            radioConductor.setSelected(false);
            txtModelo.setDisable(true);
            txtMarca.setDisable(true);
        }
    }
     public void toggleConductor(){
        if (radioPasajero.isSelected()){
            radioPasajero.setSelected(false);
            txtModelo.setDisable(false);
            txtMarca.setDisable(false);
        }
    }
     
     public void toggleMasculino(){
        if (radioFemenino.isSelected()){
            radioFemenino.setSelected(false);
        }
    }
     public void toggleFemenino(){
        if (radioMasculino.isSelected()){
            radioMasculino.setSelected(false);
        }
    }
     
    public void guardar(){
        
        if (radioConductor.isSelected()){
            
            Carro registro1 = new Carro();
            registro1.setMarca(txtMarca.getText());
            registro1.setModelo(Integer.parseInt(txtModelo.getText()));
            registro1.setCantAsientos(4);
            
            try{
                PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_AgregarCarro(?,?,?)}");
                procedimiento.setString(1, registro1.getMarca());
                procedimiento.setInt(2, registro1.getModelo());
                procedimiento.setInt(3, registro1.getCantAsientos());                 
                procedimiento.execute();
            }catch(Exception e){
                e.printStackTrace();
            }
            
            Conductor registro = new Conductor();
            registro.setNombre(txtNombres.getText());
            registro.setApellidos(txtApellidos.getText());
            registro.setCorreo(txtCorreo.getText());
            registro.setUsuario(txtUsuario.getText());
            registro.setContrasenia(txtContrasenia.getText());
            registro.setEspaciosDisponibles(4);
            registro.setUbicacion(txtUbicacion.getText());
            registro.setDestino(txtDestino.getText());
            
            try{
                PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_AgregarConductor(?,?,?,?,?,?,?,?)}");
                procedimiento.setString(1, registro.getNombre());
                procedimiento.setString(2, registro.getApellidos());
                procedimiento.setString(3, registro.getCorreo());
                procedimiento.setString(4, registro.getUsuario());
                procedimiento.setString(5, registro.getContrasenia());
                procedimiento.setInt(6, 4);
                procedimiento.setString(7, registro.getUbicacion());    
                procedimiento.setString(8, registro.getDestino());  
                procedimiento.execute();
            }catch(Exception e){
                e.printStackTrace();
            }
        
        }else if (radioPasajero.isSelected()){
        
            Pasajero registro = new Pasajero();
            registro.setNombre(txtNombres.getText());
            registro.setApellidos(txtApellidos.getText());
            registro.setCorreo(txtCorreo.getText());
            registro.setUsuario(txtUsuario.getText());
            registro.setContrasenia(txtContrasenia.getText());
            registro.setUbicacion(txtUbicacion.getText());
            registro.setDestino(txtDestino.getText());
            
            try{
                PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_AgregarPasajero(?,?,?,?,?,?,?,?)}");
                procedimiento.setString(1, registro.getNombre());
                procedimiento.setString(2, registro.getApellidos());
                procedimiento.setString(3, registro.getCorreo());
                procedimiento.setDouble(4, 0.0);
                procedimiento.setString(5, registro.getUsuario());
                procedimiento.setString(6, registro.getContrasenia());
                procedimiento.setString(7, registro.getUbicacion());    
                procedimiento.setString(8, registro.getDestino());  
                procedimiento.execute();
            }catch(Exception e){
                e.printStackTrace();
            }
        }    
    }
    
    public boolean check(){
        boolean ch = false;
        if(!txtNombres.getText().equals("") && !txtApellidos.getText().equals("") && !txtCorreo.getText().equals("") && !txtContrasenia.getText().equals("") && !txtUbicacion.getText().equals("")  && !txtDestino.getText().equals("") && !txtUsuario.getText().equals("")){
            if (radioConductor.isSelected()){
                if(!txtModelo.getText().equals("") && !txtMarca.getText().equals("")){
                    ch = true;
                }else{
                    ch = false;
                    JOptionPane.showMessageDialog(null, "Algunos de los campos solicitados se encuentran vacios por favor verifique");
                }
            }else{
                ch = true;
            } 
        }else{
            ch = false;
            JOptionPane.showMessageDialog(null, "Algunos de los campos solicitados se encuentran vacios por favor verifique");
        }
        return ch;    
    }
    
    public void nuevo(){
        boolean chk = check();
        if(chk == true){
            guardar();
            limpiarControles();
        }
    }
    
    public void limpiarControles(){
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtContrasenia.setText("");
        txtUbicacion.setText("");
        txtDestino.setText("");
        txtUsuario.setText("");
        txtModelo.setText("");
        txtMarca.setText(""); 
    }
    

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void login(){
        escenarioPrincipal.login();
    }
    
}
