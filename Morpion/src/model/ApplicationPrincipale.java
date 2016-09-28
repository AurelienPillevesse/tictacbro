/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import model.persistance.SauvegardeFichier;
import model.persistance.Sauvegarde;
import model.persistance.Chargement;
import model.persistance.ChargementFichier;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Classe de l'application principale avec la liste des joueurs, la liste des couleurs et
 * les méthodes pour les gérer (ajouter, supprimer).
 */
public class ApplicationPrincipale extends IApplication{

    private Chargement chargement = new ChargementFichier();
    private Sauvegarde sauvegarde = new SauvegardeFichier();
    

    public ObservableList<Joueur> listeJoueurObservable = FXCollections.observableArrayList();
        private ListProperty<Joueur> listeJoueur = new SimpleListProperty<>(listeJoueurObservable);
        @Override
            public ObservableList getListeJoueur() {return listeJoueur;}
        public void setListeJoueur(ObservableList value) { listeJoueur.set(value);}
        public ListProperty listeJoueurProperty() {return listeJoueur;}
    
    public ObservableList<String> data = FXCollections.observableArrayList(
            "Chocolate", "Salmon", "Gold", "Coral", "Darkorchid",
            "Darkgoldenrod", "Lightsalmon", "Rosybrown",
            "Blue", "Brown", "Green", "Grey", "Pink", "Red");
    private ListProperty<String> listeCouleur = new SimpleListProperty<>(data);
    @Override
    public ObservableList getListeCouleur() {return listeCouleur;}
    
    /**
     * Constructeur d'une application principale.
     */
    public ApplicationPrincipale(){
        List<Joueur> joueurs = chargement.charger();
        joueurs.stream().forEach((j) -> {listeJoueur.add(j);});
    }

    /**
     * Permet d'ajouter un joueur.
     */
    public void ajouterJoueur() {
        listeJoueur.add(new Joueur());
        sauvegarde.sauvegarder(listeJoueur);
    }

    /**
     * Permet de supprimer un joueur.
     * @param j Le joueur que l'on souhaite supprimer.
     */
    public void supprimerJoueur(Joueur j) {
        listeJoueur.remove(j);
        sauvegarde.sauvegarder(listeJoueur);
    }
    
    /**
     * Permet de sauvegarder la liste des joueurs..
     */
    public void sauvegarde(){
        sauvegarde.sauvegarder(listeJoueur);
    }
    
    /**
     * Permet de sauvegarder la liste de joueurs.
     * @param joueurs La liste des joueurs.
     */
    public void sauvegarderAvecListe(ListProperty<Joueur> joueurs){
        sauvegarde.sauvegarder(joueurs);
    }


    @Override
    public void supprimerCouleur(String c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
