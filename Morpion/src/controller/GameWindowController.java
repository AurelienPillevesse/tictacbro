/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Case;
import model.GestionnaireDeroulementJeu;
import model.Joueur;

/**
 * FXML Controller class
 * Controller lié à la vue GameWindow.
 */
public class GameWindowController {

    private Joueur joueurA;
    private Joueur joueurB;
    

    private GestionnaireDeroulementJeu jeu;

    @FXML
    private Button buttonQuitter;

    @FXML
    private GridPane grille;

    @FXML
    private Label labelDeroulementJeu;

    private Stage thisStage;

    private MasterDetailWindowController masterDetailWindowController;
    private GameWindowController gameWindowController;
    
    boolean termine = false;

    @FXML
    private void cliqueQuitter() {
        Platform.exit();
    }

    @FXML
    private void cliqueChangerMode() {
        chargementStage("/view/MainWindow.fxml");
    }

    @FXML
    private void cliqueChangerJoueur() {
        thisStage = ((Stage) buttonQuitter.getScene().getWindow());
        Stage s = chargementNouvelleVueMasterDetail("/view/MasterDetailWindow.fxml");
        s.setTitle("TicTacBro");
        s.getIcons().add(new Image("/img/logo.png"));
        gestionNombreDeJoueurMasterDetailWindow();
        s.show();
        s.setOnCloseRequest((WindowEvent event) -> {
            masterDetailWindowController.gestionSauvegardeFermetureFenetre();
        });
        thisStage.close();
    }

    private void gestionNombreDeJoueurMasterDetailWindow() {
        if (joueurB != null) {
            masterDetailWindowController.setNombreJoueur(2);
            masterDetailWindowController.setJoueurVsIa(false);
        } else {
            masterDetailWindowController.setNombreJoueur(1);
        }
    }

    @FXML
    private void cliqueRecommencer() {
        chargementStageGameWindow();
    }
    
    private void chargementStageGameWindow() {
        thisStage = ((Stage) buttonQuitter.getScene().getWindow());
        Stage s = chargementNouvelleVueGameWindow("/view/GameWindow.fxml");
        s.setTitle("TicTacBro");
        s.getIcons().add(new Image("/img/logo.png"));
        gameWindowController.setJoueurA(joueurA);
        gameWindowController.setJoueurB(joueurB);
        gameWindowController.setGestionnaireDeroulementJeu(joueurA, joueurB);
        s.show();
        thisStage.close();
    }

    private Stage chargementNouvelleVueGameWindow(String windowPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(windowPath));
            Stage s = new Stage();
            s.setScene(new Scene(loader.load()));
            gameWindowController = loader.<GameWindowController>getController();
            return s;
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    @FXML
    private void cliqueGrille(MouseEvent e) {
        if(!termine){
            Label lab = (Label) e.getSource();
            Integer y = getCoordonneeY(lab);
            Integer x = getCoordonneeX(lab);
            termine = jeu.tourJeu(x, y);
            PlacementDesSymbolesPourChaqueCase();
            if(termine){
                labelDeroulementJeu.setVisible(true);
            }
        }
    }

    private Integer getCoordonneeY(Label lab) {
        Integer y = GridPane.getRowIndex(lab);
        if (y == null) {
            y = 0;
        }
        return y;
    }

    private Integer getCoordonneeX(Label lab) {
        Integer x = GridPane.getColumnIndex(lab);
        if (x == null) {
            x = 0;
        }
        return x;
    }

    private void PlacementDesSymbolesPourChaqueCase() {
        int i=0;
        for (Case c : jeu.getPlateau().getCases()) {
            if (c.getJoueur() != -1) {
                if (c.getJoueur() == 1) {
                    PlacementSymboleJoueurA(i);
                } else {
                    PlacementSymboleJoueurB(i);
                }
            }
            i++;
        }
    }

    private void PlacementSymboleJoueurB(int i) {
        if(joueurB != null){
            ((Label)(grille.getChildren().get(i))).setTextFill(Color.valueOf(joueurB.getCouleur()));
        }
        ((Label)(grille.getChildren().get(i))).setText("O");
    }

    private void PlacementSymboleJoueurA(int i) {
        ((Label)(grille.getChildren().get(i))).setTextFill(Color.valueOf(joueurA.getCouleur()));
        ((Label)(grille.getChildren().get(i))).setText("X");
    }

    private void chargementStage(String windowPath) {
        thisStage = ((Stage) buttonQuitter.getScene().getWindow());

        Stage s = chargementNouvelleVue(windowPath);
        s.setTitle("TicTacBro");
        s.getIcons().add(new Image("/img/logo.png"));
        s.show();
        thisStage.close();
    }

    private Stage chargementNouvelleVue(String windowPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(windowPath));
            Stage s = new Stage();
            s.setScene(new Scene(loader.load()));
            return s;
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    private Stage chargementNouvelleVueMasterDetail(String windowPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(windowPath));
            Stage s = new Stage();
            s.setScene(new Scene(loader.load()));
            masterDetailWindowController = loader.<MasterDetailWindowController>getController();
            return s;
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    private Stage getStage() {
        return thisStage;
    }

    public void setJoueurA(Joueur joueurA) {
        this.joueurA = joueurA;
    }

    public void setJoueurB(Joueur joueurB) {
        this.joueurB = joueurB;
    }
    
    public void setGestionnaireDeroulementJeu(Joueur joueurA,Joueur joueurB){
        jeu = new GestionnaireDeroulementJeu(joueurA, joueurB);
        this.joueurA = joueurA;
        this.joueurB = joueurB;
        if(this.joueurB == null){
            labelDeroulementJeu.setVisible(false);
        }
        labelDeroulementJeu.textProperty().bind(jeu.notifLabelDeroulementJeuProperty());
    }
}
