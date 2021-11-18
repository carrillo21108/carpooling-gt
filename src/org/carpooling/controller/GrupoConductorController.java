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
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.carpooling.bean.Pasajero;
import org.carpooling.sistema.Principal;
import org.carpooling.db.Conexion;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class GrupoConductorController implements Initializable {

    private Principal escenarioPrincipal;

    private ObservableList<Pasajero> pasajeros;
    @FXML
    private TableView tablaPasajeros;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidos;
    @FXML
    private TableColumn colRuta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        
    }

    public void cargarDatos() {
        tablaPasajeros.setItems(getPasajeros());
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoPasajero"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colRuta.setCellValueFactory(new PropertyValueFactory<>("correo"));
     
        
    }

    public ObservableList<Pasajero> getPasajeros() {
        ArrayList<Pasajero> lista = new ArrayList<Pasajero>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarPasajeros()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return pasajeros = FXCollections.observableList(lista);
    }

    public void expulsarPasajero() {

    }

}
