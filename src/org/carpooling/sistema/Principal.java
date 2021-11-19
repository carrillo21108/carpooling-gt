
package org.carpooling.sistema;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.carpooling.controller.*;


public class Principal extends Application {
    
    private final String PAQUETE_VISTA = "/org/carpooling/view/";
    private Stage escenarioPrincipal;
    private Scene escena;
    
    @Override
    public void start(Stage escenarioPrincipal) {
       this.escenarioPrincipal=escenarioPrincipal;
       escenarioPrincipal.setTitle("Carpooling GT");
       escenarioPrincipal.getIcons().add(new Image("/org/carpooling/images/logo.png"));
       login();
       escenarioPrincipal.show();
    }
    
    public void login(){
        try{
            LoginController login = (LoginController)cambiarEscena("LoginView.fxml", 721, 452);
            login.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaRegistro(){
        try{
            RegistroController registro = (RegistroController)cambiarEscena("RegistroView.fxml", 721, 452);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaPerfilPasajero(){
        try{
            PerfilPasajeroController registro = (PerfilPasajeroController)cambiarEscena("PerfilPasajeroView.fxml", 721, 452);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaPerfilConductor(){
        try{
            PerfilConductorController registro = (PerfilConductorController)cambiarEscena("PerfilConductorView.fxml", 721, 452);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaSolicitudesConductor(){
        try{
            SolicitudesConductorController registro = (SolicitudesConductorController)cambiarEscena("SolicitudesView.fxml", 721, 452);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaPuntosPorRecorrer(){
        try{
            PuntosPorRecorrerController registro = (PuntosPorRecorrerController)cambiarEscena("PuntosPorRecorrerView.fxml", 721, 452);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void ventanaGrupoConductor(){
        try{
            
            GrupoConductorController registro = (GrupoConductorController)cambiarEscena("GrupoConductorView.fxml", 721, 452);
            registro.setEscenarioPrincipal(this);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaBuscarConductor(){
        try{
            BuscarConductorController registro = (BuscarConductorController)cambiarEscena("BuscarConductorView.fxml", 721, 452);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaMenuPasajero(){
        try{
            MenuPasajeroController registro = (MenuPasajeroController)cambiarEscena("MenuInicioPasajeroView.fxml", 603, 379);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaMenuConductor(){
        try {
            MenuConductorController registro = (MenuConductorController)cambiarEscena("MenuInicioConductorView.fxml", 600, 400);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaPagoConductor(){
         try {
            PagoConductorController registro = (PagoConductorController)cambiarEscena("GestionDePagosConductorView.fxml", 721, 452);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaPagoPasajero(){
         try {
            PagoPasajeroController registro = (PagoPasajeroController)cambiarEscena("GestionDePagosView.fxml", 721, 452);
            registro.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws Exception{
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader();
        InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA+fxml);
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA+fxml));
        escena = new Scene((AnchorPane)cargadorFXML.load(archivo),ancho,alto);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();
        resultado = (Initializable)cargadorFXML.getController();
        
        return resultado;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
