
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.carpooling.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.carpooling.bean.Conductor;
import org.carpooling.bean.Pasajero;
import org.carpooling.sistema.Principal;
import org.carpooling.db.Conexion;


/**
 *
 * @author Usuario
 */
public class PuntosPorRecorrerController implements Initializable {
    
    private Principal escenarioPrincipal;
    private int codigoUsuario;
    private ObservableList<Pasajero> pasajeros;
    @FXML
    private TableView tablaPasajeros;
    @FXML
    private TableColumn colCodigo;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colRuta;
    @FXML
    private Button btnIniciarViaje;
    @FXML
    private Button btnFinalizarViaje;
    @FXML
    private Label txtMonto;
    @FXML
    private Label txtTiempo;

    private long tiempoInicial;
    private long tiempoTranscurrido;
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnFinalizarViaje.setDisable(true);
          try {
            // Query para obtener codigo de conductor actual
            PreparedStatement procedimientoToken = Conexion.getInstancia().getConexion().prepareCall("{call sp_GetToken()}");
            ResultSet token = procedimientoToken.executeQuery();

            while (token.next()) {
                codigoUsuario = token.getInt("codigoUsuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        cargarDatos();
    }
    
    public void cargarDatos() {
        tablaPasajeros.setItems(getPasajeros());
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoPasajero"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRuta.setCellValueFactory(new PropertyValueFactory<>("destino"));

    }
    
    //metodo para obtener pasajeros con el codigo de conductor correspondiente
    public ObservableList<Pasajero> getPasajeros() {
        ArrayList<Pasajero> lista = new ArrayList<Pasajero>();

        try {
            PreparedStatement procedimiento = Conexion.getInstancia().getConexion().prepareCall("{call sp_ListarGrupo(?)}");
            procedimiento.setInt(1, codigoUsuario);
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
    
    public void salir(){
        escenarioPrincipal.ventanaMenuConductor();
    }
    
    public void finalizarViaje(){
        btnFinalizarViaje.setDisable(true);
        btnIniciarViaje.setDisable(false);
        
        tiempoTranscurrido = System.currentTimeMillis() - tiempoInicial;
        int segundosTotales = (int) TimeUnit.MILLISECONDS.toSeconds(tiempoTranscurrido);
        int horas = segundosTotales/3600;
        int minutos = (segundosTotales % 3600)/60;
        int segundos = segundosTotales % 60;
        int km = Integer.valueOf(JOptionPane.showInputDialog("Ingrese la distancia recorrida en km"));
        
        double total = 5 + (0.6*minutos) + (1.19 * km);
        txtTiempo.setText(String.format("%02d:%02d:%02d",horas,minutos,segundos));
        txtMonto.setText("Q"+String.valueOf(total));
        
    }
    
    public void iniciarViaje(){
        
        btnIniciarViaje.setDisable(true);
        btnFinalizarViaje.setDisable(false);
        
        tiempoInicial = System.currentTimeMillis();
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
}
