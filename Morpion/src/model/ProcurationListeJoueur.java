/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe permettant la procuration de la liste des joueurs.
 */
public class ProcurationListeJoueur extends IApplication{
    private ObservableList<Joueur> listeJoueur;
    private ObservableList<String> data;
    private Joueur joueur;
    private String couleur;
    
    /**
     * Constructeur de la procuration de la liste des joeuurs.
     * @param app Une ApplicationPrincipale qui contient la vraie liste de joueurs.
     * @param joueurVsIa Un booléen, true si le joueur joue contre une IA.
     * @param nombreDeJoueur Un entier qui correspond au nombre de joueur.
     */
    public ProcurationListeJoueur(ApplicationPrincipale app, boolean joueurVsIa, int nombreDeJoueur) {
        this.app = app;
        this.joueurVsIa = joueurVsIa;
        data = app.getListeCouleur();
        this.nombreDeJoueur = nombreDeJoueur;
        listeJoueur = FXCollections.observableArrayList(app.getListeJoueur());
    }

    /**
     * Permet de supprimer un joueur dans la procuration.
     * @param j Un joueur qui correspond au joueur à supprimer.
     */
    public void supprimerJoueur(Joueur j) {
        joueur = j;
    }

    /**
     * Retourne la liste des joueurs de la procuration avec un joueur en moins de disponible si il y a deux joueurs humains.
     * @return La liste des joueurs de la liste de procuration.
     */
    @Override
    public ObservableList<Joueur> getListeJoueur() {
        listeJoueur = FXCollections.observableArrayList(app.getListeJoueur());
        if (joueurVsIa && nombreDeJoueur != 2) {
            listeJoueur.remove(joueur);
        }
        return listeJoueur;
    }
    
    /**
     * Permet de supprimer une couleur dans la procuration.
     * @param c Un string qui correspond à la couleur à supprimer.
     */
    public void supprimerCouleur(String c) {
        couleur = c;
    }
    
    /**
     * Retourne la liste des couleurs de la procuration avec une couleur en moins de disponible si il y a deux joueurs humains.
     * @return La liste des joueurs de la liste de procuration.
     */
    @Override
    public ObservableList<String> getListeCouleur() {
        data = app.getListeCouleur();
        if(joueurVsIa && nombreDeJoueur != 2) {
            data.remove(couleur);
        }
        return data;
    }
}
