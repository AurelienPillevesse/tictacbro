/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 * Controller lié à la vue MainWindow.
 */
public class MainWindowController {

    @FXML
    private Button buttonValider;

    @FXML
    private RadioButton radioUnJoueur;

    private static Stage thisStage;

    private FXMLLoader loader;
    
    private MasterDetailWindowController masterDetailWindowController;

    @FXML
    public void cliqueValider() {
        thisStage = ((Stage) buttonValider.getScene().getWindow());
        Stage s = chargementNouvelleVue();
        s.setTitle("TicTacBro");
        s.getIcons().add(new Image("/img/logo.png"));
        s.show();
        s.setOnCloseRequest((WindowEvent event)-> {masterDetailWindowController.gestionSauvegardeFermetureFenetre();});
        thisStage.close();
    }

    private Stage chargementNouvelleVue() {
        try {
            loader = new FXMLLoader(getClass().getResource("/view/MasterDetailWindow.fxml"));
            Stage s = new Stage();
            s.setScene(new Scene(loader.load()));
            gestionNombreDeJoueurNouvelleVue(loader);
            return s;
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    private void gestionNombreDeJoueurNouvelleVue(FXMLLoader loader) {
        masterDetailWindowController = loader.<MasterDetailWindowController>getController();
        if (radioUnJoueur.isSelected()) {
            masterDetailWindowController.setNombreJoueur(1);
        } else {
            masterDetailWindowController.setNombreJoueur(2);
            masterDetailWindowController.setJoueurVsIa(false);
        }
    }

    public static Stage getStage() {
        return thisStage;
    }
}
