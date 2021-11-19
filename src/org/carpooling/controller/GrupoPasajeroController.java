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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.carpooling.bean.Conductor;
import org.carpooling.bean.Pasajero;
import org.carpooling.db.Conexion;
import org.carpooling.sistema.Principal;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class GrupoPasajeroController implements Initializable {

    private Principal escenarioPrincipal;

    private int codigoPasajeroActual;
    private ObservableList<Pasajero> pasajeros;
    private ObservableList<Conductor> conductor;
    // componentes tabla de conductores
    @FXML
    private TableView tablaConductores;
    @FXML
    private TableColumn colCodigoC;
    @FXML
    private TableColumn colNombreC;
    @FXML
    private TableColumn colApellidosC;
    @FXML
    private TableColumn colRutaC;

    // componentes tabla de pasajeros
    @FXML
    private TableView tablaPasajeros;
    @FXML
    private TableColumn colCodigoP;
    @FXML
    private TableColumn colNombreP;
    @FXML
    private TableColumn colApellidosP;
    @FXML
    private TableColumn colRutaP;
    private Pasajero pasajeroActual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Query para obtener codigo de conductor actual
            PreparedStatement procedimientoToken = Conexion.getInstancia().getConexion().prepareCall("{call sp_GetToken()}");
            ResultSet token = procedimientoToken.executeQuery();

            while (token.next()) {
                codigoPasajeroActual = token.getInt("codigoUsuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        obtenerPasajeroActual();
        cargarDatos();
    }

    public void cargarDatos() {
        //cargar datos de tablaPasajeros
        tablaPasajeros.setItems(getPasajeros());
        colCodigoP.setCellValueFactory(new PropertyValueFactory<>("codigoPasajero"));
        colNombreP.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidosP.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colRutaP.setCellValueFactory(new PropertyValueFactory<>("destino"));
        tablaConductores.setItems(getConductor());
        colCodigoC.setCellValueFactory(new PropertyValueFactory<>("codigoConductor"));
        colNombreC.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidosC.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colRutaC.setCellValueFactory(new PropertyValueFactory<>("destino"));
    }

    public void abandonarGrupo() {

        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea abandonar el grupo?", "Abandonar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ExpulsarPasajero(?)}");
                procedimiento.setInt(1, pasajeroActual.getCodigoPasajero());
                procedimiento.execute();
                pasajeros.clear();
                conductor.clear();  
                tablaPasajeros.getSelectionModel().select(null);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            tablaPasajeros.getSelectionModel().select(null);
        }

    }

    public ObservableList<Conductor> getConductor() {
        ArrayList<Conductor> lista = new ArrayList<Conductor>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BuscarConductor(?)}");
            procedimiento.setInt(1, pasajeroActual.getCodigoConductor());
            ResultSet conductorActual = procedimiento.executeQuery();

            while (conductorActual.next()) {
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

                lista.add(new Conductor(codigoConductor, nombre, apellidos, correo, usuario, contrasenia, ubicacion, destino, codigoAuto, espaciosDisponibles));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conductor = FXCollections.observableList(lista);
    }

    public ObservableList<Pasajero> getPasajeros() {
        ArrayList<Pasajero> lista = new ArrayList<Pasajero>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarGrupo(?)}");
            procedimiento.setInt(1, pasajeroActual.getCodigoConductor());
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

    public void obtenerPasajeroActual() {
        try {

            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_BuscarPasajero(?)}");
            procedimiento.setInt(1, codigoPasajeroActual);
            ResultSet pasajero = procedimiento.executeQuery();
            while (pasajero.next()) {
                int codigoPasajero = pasajero.getInt("codigoPasajero");
                String nombre = pasajero.getString("nombre");
                String apellidos = pasajero.getString("apellidos");
                String correo = pasajero.getString("correo");
                int codigoConductor = pasajero.getInt("codigoConductor");
                int deuda = pasajero.getInt("deuda");
                String usuario = pasajero.getString("usuario");
                String contrasenia = pasajero.getString("contrasenia");
                String ubicacion = pasajero.getString("ubicacion");
                String destino = pasajero.getString("destino");
                pasajeroActual = new Pasajero(codigoPasajero, nombre, apellidos, correo, codigoConductor, deuda, usuario, contrasenia, ubicacion, destino);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void salir() {
        this.escenarioPrincipal.ventanaMenuPasajero();
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

}
