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
import org.carpooling.bean.Pasajero;
import org.carpooling.db.Conexion;
import org.carpooling.sistema.Principal;

/**
 *
 * @author Usuario
 */
public class PagoConductorController implements Initializable{
    
    
    private Principal escenarioPrincipal;
    
    private ObservableList<Pasajero> listaPasajero;
    
    @FXML private Button btnSalir;
    @FXML private TableView tblPasajero;
    @FXML private TableColumn colNombres;
    @FXML private TableColumn colDeuda;
    @FXML private TableColumn colRuta;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    
    public ObservableList<Pasajero> getPasajeros(){
        ArrayList<Pasajero> lista = new ArrayList<Pasajero>();
        try{
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarPasajeros}");
            ResultSet resultado = procedimiento.executeQuery() ;
            while(resultado.next()){
                    lista.add(new Pasajero(resultado.getInt("codigoPasajero"), 
                                        resultado.getString("nombre"),
                                        resultado.getString("apellidos"),
                                        resultado.getString("correo"),
                                        resultado.getInt("codigoConductor"),
                                        resultado.getInt("deuda"),
                                        resultado.getString("usuario"),
                                        resultado.getString("contrasenia"),
                                        resultado.getString("ubicacion"),
                                        resultado.getString("destino")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaPasajero = FXCollections.observableList(lista);
    }
    
    
    public void cargarDatos(){
        tblPasajero.setItems(getPasajeros());
        colNombres.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("nombre"));
        colDeuda.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("deuda"));
        colRuta.setCellValueFactory(new PropertyValueFactory<Pasajero, String>("ruta"));
    }
    

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void realizarPago(){
        
        
    }
    
}
