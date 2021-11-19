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
import org.carpooling.bean.Deuda;
import org.carpooling.bean.Pasajero;
import org.carpooling.bean.Solicitud;
import org.carpooling.db.Conexion;
import org.carpooling.sistema.Principal;

/**
 *
 * @author Usuario
 */
public class PagoPasajeroController implements Initializable {
    
    private ObservableList<Deuda>listaDeudas;
      
    private Principal escenarioPrincipal;
    @FXML private Button btnPagar;
    @FXML private Button btnSalir;
    
    @FXML private TableView tblDeudas;
    
    @FXML private TableColumn colNo;
    @FXML private TableColumn colMonto;
    @FXML private TableColumn colNombre;
    @FXML private TableColumn colRuta;
    
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
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        cargarDatos();
    }
    
    
    public void cargarDatos(){
        tblDeudas.setItems(getDeudas());
        colNo.setCellValueFactory(new PropertyValueFactory<Deuda, Integer>("codigoDeuda"));
        colMonto.setCellValueFactory(new PropertyValueFactory<Deuda, Double>("monto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Deuda, String>("nombreConductor"));
        colRuta.setCellValueFactory(new PropertyValueFactory<Deuda, String>("ruta"));
    }
    
    
    public ObservableList<Deuda> getDeudas(){
        ArrayList<Deuda> lista = new ArrayList<Deuda>();
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ObtenerDeudas(?)}");
            procedimiento.setInt(1, pasajero.getCodigoPasajero());
            ResultSet resultado = procedimiento.executeQuery() ;
            while(resultado.next()){
                    lista.add(new Deuda(resultado.getInt("codigoDeuda"), 
                                        resultado.getInt("codigoPasajero"),
                                        resultado.getString("ruta"),
                                        resultado.getDouble("monto"),
                                        resultado.getString("nombreConductor"),
                                        resultado.getInt("codigoConductor")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDeudas = FXCollections.observableList(lista);
    }
    
    
    
    public void pagarDeuda(){

        if(tblDeudas.getSelectionModel().getSelectedItem() != null){
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea pagar el viaje?", "Pago de deuda", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(respuesta == JOptionPane.YES_OPTION){
                try{
                    PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_EliminarDeuda(?,?)}");
                    procedimiento.setInt(1,((Deuda)tblDeudas.getSelectionModel().getSelectedItem()).getCodigoDeuda());
                    procedimiento.setDouble(2,((Deuda)tblDeudas.getSelectionModel().getSelectedItem()).getMonto());
                    procedimiento.execute();
                    listaDeudas.remove(tblDeudas.getSelectionModel().getSelectedIndex());
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }

    }
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void realizarPago(){
        
    }
    
    public void salir(){
        escenarioPrincipal.ventanaMenuPasajero(); 
    }
}
