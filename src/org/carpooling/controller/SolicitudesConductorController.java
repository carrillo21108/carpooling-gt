/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.carpooling.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.carpooling.sistema.Principal;
/**
 *
 * @author Usuario
 */
public class SolicitudesConductorController implements Initializable {
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO};
    private operaciones tipoDeOperacion=operaciones.NINGUNO;
      
    private Principal escenarioPrincipal;
    @FXML private Button btnAceptar;
    @FXML private Button btnDenegar;
    
    @FXML private TableView tblSolicitudes;
    
    @FXML private TableColumn colCodigo;
    @FXML private TableColumn colNombres;
    @FXML private TableColumn colApellidos;
    @FXML private TableColumn colUbicacion;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    public void cargarDatos(){
        /*tblSolicitudes.setItems(getPacientes());
        colCodigo.setCellValueFactory(new PropertyValueFactory<Paciente,Integer>("codigoPaciente"));
        colDPI.setCellValueFactory(new PropertyValueFactory<Paciente,String>("DPI"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Paciente,String>("apellidos"));
        colNombres.setCellValueFactory(new PropertyValueFactory<Paciente,String>("nombres"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<Paciente,Date>("fechaNacimiento"));
        colEdad.setCellValueFactory(new PropertyValueFactory<Paciente,Integer>("edad"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Paciente,String>("direccion"));
        colOcupacion.setCellValueFactory(new PropertyValueFactory<Paciente,String>("ocupacion"));
        colSexo.setCellValueFactory(new PropertyValueFactory<Paciente,String>("sexo"));*/
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void obtenerSolicitudes(){
        
    }
}
