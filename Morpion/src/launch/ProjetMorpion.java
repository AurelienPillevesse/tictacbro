/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Classe qui étends Application, c'est elle qui lance le programme et affiche la première vue 
 * pour choisir le nombre de joueurs.
 */
public class ProjetMorpion extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TicTacBro");
        stage.getIcons().add(new Image(("img/logo.png")));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
