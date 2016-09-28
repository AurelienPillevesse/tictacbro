/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.ObservableList;

/**
 * Classe abstraite de l'application utilisée pour le patron Procuration.
 */
public abstract class IApplication {
    protected ApplicationPrincipale app;
    protected boolean joueurVsIa;
    protected int nombreDeJoueur;
    public abstract void supprimerJoueur(Joueur j);
    public abstract void supprimerCouleur(String c);
    public abstract ObservableList<Joueur> getListeJoueur();
    public abstract ObservableList<String> getListeCouleur();
    public void setApp(ApplicationPrincipale app) {
        this.app = app;
    }
}
