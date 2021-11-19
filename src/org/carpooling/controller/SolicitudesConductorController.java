/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.carpooling.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.carpooling.bean.Conductor;
import org.carpooling.bean.Solicitud;
import org.carpooling.db.Conexion;
import org.carpooling.sistema.Principal;
/**
 *
 * @author Usuario
 */
public class SolicitudesConductorController implements Initializable {
    
    private ObservableList<Solicitud>listaSolicitudes;
      
    private Principal escenarioPrincipal;
    @FXML private Button btnAceptar;
    @FXML private Button btnDenegar;
    @FXML private Button btnSalir;
    
    @FXML private TableView tblSolicitudes;
    
    @FXML private TableColumn colCodigo;
    @FXML private TableColumn colNombre;
    @FXML private TableColumn colApellidos;
    @FXML private TableColumn colUbicacion;
    
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
                cargarDatos();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void cargarDatos(){
        tblSolicitudes.setItems(getSolicitudes());
        colCodigo.setCellValueFactory(new PropertyValueFactory<Solicitud,Integer>("codigoSolicitud"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Solicitud,String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Solicitud,String>("apellidos"));
        colUbicacion.setCellValueFactory(new PropertyValueFactory<Solicitud,String>("ubicacion"));
    }
    
    public ObservableList<Solicitud> getSolicitudes(){
        ArrayList<Solicitud> lista= new ArrayList<Solicitud>();
        
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ObtenerSolicitudes(?)}");
            procedimiento.setInt(1, conductor.getCodigoConductor());
            ResultSet resultado = procedimiento.executeQuery();
            
            while(resultado.next()){
                lista.add(new Solicitud(resultado.getInt("codigoSolicitud"), resultado.getInt("codigoPasajero"), resultado.getString("nombre"), resultado.getString("apellidos"), resultado.getString("ubicacion"), resultado.getInt("codigoConductor")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return listaSolicitudes = FXCollections.observableList(lista);
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void aceptar(){
        if(tblSolicitudes.getSelectionModel().getSelectedItem() != null){
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea aceptar la solicitud?", "Aceptar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
                try{
                PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_AceptarSolicitud(?,?,?)}");
                procedimiento.setInt(1, ((Solicitud)tblSolicitudes.getSelectionModel().getSelectedItem()).getCodigoSolicitud());
                procedimiento.setInt(2, ((Solicitud)tblSolicitudes.getSelectionModel().getSelectedItem()).getCodigoPasajero());
                procedimiento.setInt(3, ((Solicitud)tblSolicitudes.getSelectionModel().getSelectedItem()).getCodigoConductor());
                procedimiento.execute();
                listaSolicitudes.remove(tblSolicitudes.getSelectionModel().getSelectedIndex());
                tblSolicitudes.getSelectionModel().select(null);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                tblSolicitudes.getSelectionModel().select(null);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento.");
        }
    }
    
    public void rechazar(){
        if(tblSolicitudes.getSelectionModel().getSelectedItem() != null){
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea rechazar la solicitud?", "Rechazar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta==JOptionPane.YES_OPTION){
                try{
                PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_EliminarSolicitud(?)}");
                procedimiento.setInt(1, ((Solicitud)tblSolicitudes.getSelectionModel().getSelectedItem()).getCodigoSolicitud());
                procedimiento.execute();
                listaSolicitudes.remove(tblSolicitudes.getSelectionModel().getSelectedIndex());
                tblSolicitudes.getSelectionModel().select(null);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                tblSolicitudes.getSelectionModel().select(null);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento.");
        }
    }
}
