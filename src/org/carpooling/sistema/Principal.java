
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
import org.carpooling.controller.LoginController;
import org.carpooling.controller.RegistroController;
import org.carpooling.controller.PerfilController;


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
    
    public void ventanaPerfil(){
        try{
            PerfilController registro = (PerfilController)cambiarEscena("PerfilView.fxml", 721, 452);
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
