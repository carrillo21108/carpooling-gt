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
import org.carpooling.bean.Pasajero;
import org.carpooling.db.Conexion;
import org.carpooling.sistema.Principal;

public class BuscarConductorController implements Initializable {

    private Principal escenarioPrincipal;
    
    private ObservableList<Conductor> listaConductor;
    
    @FXML private Button btnVerPerfil;
    @FXML private Button btnEnviar;
    @FXML private Button btnSalir;
    @FXML private TableView tblConductores;
    @FXML private TableColumn colNo;
    @FXML private TableColumn colNombres;
    @FXML private TableColumn colApellidos;
    @FXML private TableColumn colRuta;
    
    private ResultSet token;
    private ResultSet pasajeroActual;
    private Pasajero pasajero = null;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        cargarDatos();
    }  
    
    
    public void cargarDatos(){
        tblConductores.setItems(getConductores());
        colNo.setCellValueFactory(new PropertyValueFactory<Conductor, Integer>("codigoConductor"));
        colNombres.setCellValueFactory(new PropertyValueFactory<Conductor, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Conductor, String>("apellidos"));
        colRuta.setCellValueFactory(new PropertyValueFactory<Conductor, String>("destino"));
    }
    
    
    public ObservableList<Conductor> getConductores(){
        ArrayList<Conductor> lista = new ArrayList<Conductor>();
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarConductores}");
            ResultSet resultado = procedimiento.executeQuery() ;
            while(resultado.next()){
                    lista.add(new Conductor(resultado.getInt("codigoConductor"), 
                                        resultado.getString("nombre"),
                                        resultado.getString("apellidos"),
                                        resultado.getString("correo"),
                                        resultado.getString("usuario"),
                                        resultado.getString("contrasenia"),
                                        resultado.getString("ubicacion"),
                                        resultado.getString("destino"),
                                        resultado.getInt("codigoCarro"),
                                        resultado.getInt("espaciosDisponibles")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaConductor = FXCollections.observableList(lista);
    }
    
    public void enviarSolicitud(){

        if(tblConductores.getSelectionModel().getSelectedItem() != null){
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea enviar la solicitud?", "Enviar Solicitud", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta == JOptionPane.YES_OPTION){
                try{
                    PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_AgregarSolicitud(?,?,?,?,?)}");
                    procedimiento.setInt(1,pasajero.getCodigoPasajero());
                    procedimiento.setString(2,pasajero.getNombre());
                    procedimiento.setString(3,pasajero.getApellidos());
                    procedimiento.setString(4,pasajero.getUbicacion());
                    procedimiento.setInt(5,((Conductor)tblConductores.getSelectionModel().getSelectedItem()).getCodigoConductor());
                    procedimiento.execute();
                    listaConductor.remove(tblConductores.getSelectionModel().getSelectedIndex());
                    salir();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }

    }
    
    public void salir(){
        escenarioPrincipal.ventanaMenuPasajero(); 
    }
    
     public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    
    
    
    
}
