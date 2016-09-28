/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.ApplicationPrincipale;
import model.IApplication;
import model.Joueur;
import model.ProcurationListeJoueur;

/**
 * FXML Controller class
 * Controller lié à la vue MasterDetailWindow.
 */
public class MasterDetailWindowController {

    @FXML
    private ComboBox couleurJoueur;

    private int nombreDeJoueur;
    private boolean joueurVsIa = true;

    private static Stage thisStage;
    
    private Joueur joueurA;
    private Joueur joueurB;

    private ContextMenu contextMenu = new ContextMenu();
    private MenuItem supprimerContextMenu = new MenuItem("Supprimer");

    private ListView<String> list = new ListView<>();

    @FXML
    private ApplicationPrincipale app;

    private IApplication procuration;
    
    @FXML
    private Button buttonLancerPartie;

    @FXML
    private TextField nomJoueur;

    @FXML
    private TextField prenomJoueur;

    @FXML
    private Label nombreParties;

    @FXML
    private Label partieGagne;

    @FXML
    private Label partieNul;

    @FXML
    private Label partiePerdu;

    @FXML
    private Label ratio;

    @FXML
    private ListView listeJoueurs;

    private MasterDetailWindowController masterDetailWindowController;
    private GameWindowController gameWindowController;

    @FXML
    private void cliqueAjouterJoueur() {
        app.ajouterJoueur();
        procuration.getListeJoueur();
        affichageListeJoueurs();
    }

    @FXML
    private void cliqueRetour() {
        app.sauvegarde();
        chargementStage("/view/MainWindow.fxml");
    }

    @FXML
    private void cliqueLancerPartie() {
        app.sauvegarde();
        if (nombreDeJoueur == 2) {
            chargementStageMasterDetail();
        } else {
            chargementStageGameWindow();
        }
    }

    public void initialize() {
        procuration = new ProcurationListeJoueur(app, joueurVsIa, nombreDeJoueur);
        listeJoueurs.setPlaceholder(new Label("Aucun joueur dans la liste"));
        bindingListeJoueurs();
        affichageListeJoueurs();
        gestionListeCouleurDetail();
        gestionContextMenu();
    }
    

    public void chargementStage(String windowPath) {
        thisStage = ((Stage) nomJoueur.getScene().getWindow());
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
    
    private void chargementStageMasterDetail() {
        thisStage = ((Stage) buttonLancerPartie.getScene().getWindow());
        Stage s = chargementNouvelleVueMasterDetail("/view/MasterDetailWindow.fxml");
        s.setTitle("TicTacBro");
        s.getIcons().add(new Image("/img/logo.png"));
        gestionChargementParametresNouvelleFenetreMasterDetailWindow();
        s.show();
        thisStage.close();
    }

    private void gestionChargementParametresNouvelleFenetreMasterDetailWindow() {
        masterDetailWindowController.setJoueurA(((Joueur)(listeJoueurs.getSelectionModel().getSelectedItem())));
        masterDetailWindowController.setJoueurVsIa(false);
        masterDetailWindowController.setNombreJoueur(nombreDeJoueur - 1);
        procuration.supprimerCouleur((String)(couleurJoueur.getSelectionModel().getSelectedItem()));
        procuration.supprimerJoueur((Joueur)(listeJoueurs.getSelectionModel().getSelectedItem()));
        masterDetailWindowController.setProcuration((ProcurationListeJoueur) procuration);
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
    
    private void chargementStageGameWindow() {
        thisStage = ((Stage) buttonLancerPartie.getScene().getWindow());
        Stage s = chargementNouvelleVueGameWindow("/view/GameWindow.fxml");
        s.setTitle("TicTacBro");
        s.getIcons().add(new Image("/img/logo.png"));
        if(joueurVsIa) {
            gameWindowController.setGestionnaireDeroulementJeu(((Joueur)(listeJoueurs.getSelectionModel().getSelectedItem())), null);
        } else {
            gameWindowController.setGestionnaireDeroulementJeu(joueurA, ((Joueur)(listeJoueurs.getSelectionModel().getSelectedItem())));
        }
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

    private void gestionListeCouleurDetail() {
        couleurJoueur.setItems(app.data);
        couleurJoueur.setCellFactory((Object param) -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Rectangle rect = new Rectangle(100, 20);
                    rect.setFill(Color.web(item));
                    setGraphic(rect);
                }
            }
        });
    }

    private void gestionContextMenu() {
        contextMenu.getItems().add(supprimerContextMenu);
        listeJoueurs.setContextMenu(contextMenu);
        supprimerContextMenu.setOnAction((ActionEvent event) -> {
            gestionErreurSelectionJoueur();
        });
    }

    public void gestionSauvegardeFermetureFenetre() {
        app.sauvegarde();
    }

    private void gestionErreurSelectionJoueur() {
        try {
            app.supprimerJoueur((Joueur) listeJoueurs.getSelectionModel().getSelectedItem());
            procuration.getListeJoueur();
            affichageListeJoueurs();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Vous devez sélectionner un joueur dans la liste pour effectuer cette action.");
            alert.show();
        }
    }

    private void bindingListeJoueurs() {
        listeJoueurs.getSelectionModel().selectedItemProperty().addListener((o, oldV, newV) -> {
            if (oldV != null) {
                unbindJoueur((Joueur) oldV);
            }
            if (newV != null) {
                bindJoueur((Joueur) newV);
            }
        }
        );
    }

    private void unbindJoueur(Joueur oldV) {
        nomJoueur.textProperty().unbindBidirectional(oldV.nomProperty());
        prenomJoueur.textProperty().unbindBidirectional(oldV.prenomProperty());
        couleurJoueur.valueProperty().unbindBidirectional(oldV.couleurProperty());
        ratio.textProperty().unbind();
        nombreParties.textProperty().unbind();
        partieGagne.textProperty().unbind();
        partieNul.textProperty().unbind();
        partiePerdu.textProperty().unbind();
    }

    private void bindJoueur(Joueur newV) {
        nomJoueur.textProperty().bindBidirectional(newV.nomProperty());
        prenomJoueur.textProperty().bindBidirectional(newV.prenomProperty());
        nombreParties.textProperty().bind(newV.nombrePartieProperty().asString());
        partieGagne.textProperty().bind(newV.nombreGagneProperty().asString());
        partiePerdu.textProperty().bind(newV.nombrePerduProperty().asString());
        partieNul.textProperty().bind(newV.nombreNulProperty().asString());
        ratio.textProperty().bind(newV.ratioProperty().asString());
        gestionCouleurDejaSelectionne(newV);
    }

    private void gestionCouleurDejaSelectionne(Joueur newV) {
        if(procuration.getListeCouleur().contains((newV.getCouleur()))){
            couleurJoueur.valueProperty().bindBidirectional(newV.couleurProperty());
        } else {
            newV.setCouleur(couleurJoueur.getItems().get(0).toString());
            couleurJoueur.valueProperty().bindBidirectional(newV.couleurProperty());
        }
    }

    private void affichageListeJoueurs() {
        bindingListViewAvecListeJoueur();
        listeJoueurs.setCellFactory((Object param) -> new ListCell<Joueur>() {
            @Override
            protected void updateItem(Joueur item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    textProperty().unbind();
                    setText("");
                } else {
                    textProperty().bind(Bindings.concat(item.nomProperty(), ' ', item.prenomProperty()));
                }
            }
        });
    }

    private void bindingListViewAvecListeJoueur() {
        listeJoueurs.setItems(procuration.getListeJoueur());
        couleurJoueur.setItems(procuration.getListeCouleur());
    }

    public void setNombreJoueur(int nbJoueur) {
        nombreDeJoueur = nbJoueur;
        if(nombreDeJoueur != 1){
            buttonLancerPartie.setText("Suivant");
        }
    }

    public void setJoueurVsIa(boolean joueurVsIa) {
        this.joueurVsIa = joueurVsIa;
    }

    public void setJoueurA(Joueur joueurA) {
        this.joueurA = joueurA;
    }
    
    public void setJoueurB(Joueur joueurB) {
        this.joueurB = joueurB;
    }

    
    public void setProcuration(ProcurationListeJoueur procuration) {
        this.procuration = procuration;
        this.procuration.setApp(app);
        affichageListeJoueurs();
    }

    public static Stage getStage() {
        return thisStage;
    }
}
