/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.Random;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.gestionIA.GestionIAFacile;
import model.gestionIA.Gestionnaire;
import javafx.scene.control.Button;

/**
 * Classe concernant le déroulement du jeu en lui-même.
 */
public class GestionnaireDeroulementJeu {

    private static Random rand;
    private Plateau plateau = new Plateau3x3();
    private Iterateur iterateur;
    private int joueur;
    private Joueur joueurA;
    private Joueur joueurB;
    private Gestionnaire joueurIA;
    private ApplicationPrincipale app = new ApplicationPrincipale();

    private final StringProperty notifLabelDeroulementJeu = new SimpleStringProperty();
        public String getNotifLabelDeroulementJeu() {return notifLabelDeroulementJeu.get();}
        public void setNotifLabelDeroulementJeu(String value) {notifLabelDeroulementJeu.set(value);}
        public StringProperty notifLabelDeroulementJeuProperty() {return notifLabelDeroulementJeu;}

    /**
     * Constructeur du gestionnaire de déroulement du jeu.
     * @param A Un joueur qui est le premier joueur sélectionné.
     * @param B Un joueur qui est le second joueur sélectionné. Peut être "null" si l'utilisateur joue contre l'ordinateur.
     */
    public GestionnaireDeroulementJeu(Joueur A, Joueur B) {
        iterateur = plateau.getIterateur();
        joueurA = A;
        joueurB = B;
        rand = new Random();
        joueur = (rand.nextInt(2)) + 1;
        if (joueurB == null) {
            joueurIA = new GestionIAFacile();
            joueur = 1;
        }
        gestionLabelCommencementPartie();
    }

    private void gestionLabelCommencementPartie() {
        if (joueur == 1) {
            setNotifLabelDeroulementJeu("C'est au tour de " + joueurA + " de jouer !");
        } else {
            setNotifLabelDeroulementJeu("C'est au tour de " + joueurB + " de jouer !");
        }
    }

    /**
     * Permet de savoir si un joueur à gagner ou non.
     * @return Un booléen, true si un joueur a gagné.
     */
    public Boolean victoire() {
        iterateur.start();
        List<Case> cases;
        while (iterateur.hasNext()) {
            cases = iterateur.next();
            int joueur = cases.get(0).getJoueur();
            if (joueur != -1 && cases.get(1).getJoueur() == joueur && cases.get(2).getJoueur() == joueur) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retourne un booléen qui permet de savoir si la partie est finie ou non.
     * @param x Un entier qui est l'abscisse de la case sélectionnée.
     * @param y Un entier qui est l'ordonnée de la case sélectionnée.
     * @return Un booleen, true si la partie est terminé.
     */
    public boolean tourJeu(int x, int y) {
        if (joueur == 1) {
            return gestionTourJoueurA(x, y);
        } else {
            return gestionTourJoueurB(x, y);
        }
    }

    private boolean gestionTourJoueurB(int x, int y) {
        plateau.placerSurCase(x, y, joueur);
        if (verificationVictoireJoueurB()) {
            return true;
        }
        setNotifLabelDeroulementJeu("C'est au tour de " + joueurA + " de jouer !");
        joueur--;
        return false;
    }

    private boolean verificationVictoireJoueurB() {
        if (victoire()) {
            setNotifLabelDeroulementJeu(joueurB + " a gagné !");
            sauvegarder(joueurB, false);
            return true;
        }
        if (plateau.egalite()) {
            setNotifLabelDeroulementJeu("Egalité");
            sauvegarder(null, true);
            return true;
        }
        return false;
    }

    private boolean gestionTourJoueurA(int x, int y) {
        plateau.placerSurCase(x, y, joueur);
        if (verificationFinDuJeuJoueurA()) {
            return true;
        }
        if (joueurB != null) {
            setNotifLabelDeroulementJeu("C'est au tour de " + joueurB + " de jouer !");
            joueur++;
        } else {
            return gestionTourIA();
        }
        return false;
    }

    private boolean verificationFinDuJeuJoueurA() {
        if (victoire()) {
            setNotifLabelDeroulementJeu(joueurA + " a gagné !");
            sauvegarder(joueurA, false);
            return true;
        }
        if (plateau.egalite()) {
            setNotifLabelDeroulementJeu("Egalité");
            sauvegarder(null, true);
            return true;
        }
        return false;
    }

    private boolean gestionTourIA() {
        joueurIA.jouer(plateau);
        if (victoire()) {
            setNotifLabelDeroulementJeu("L'ordinateur a gagné !");
            sauvegarder(null, false);
            return true;
        }
        if (plateau.egalite()) {
            setNotifLabelDeroulementJeu("Egalité");
            sauvegarder(null, true);
            return true;
        }
        return false;
    }

    private void sauvegarder(Joueur gagnant, boolean egalite) {
        ListProperty<Joueur> listeJoueur = (ListProperty<Joueur>) app.getListeJoueur();
        for (Joueur j : listeJoueur) {
            j = ajoutStatistiquesJoueurA(j,gagnant, egalite);
            if (joueurB != null) {
                j = ajoutStatistiquesJoueurB(j, gagnant, egalite);
            }
        }
        app.sauvegarderAvecListe(listeJoueur);
    }

    private Joueur ajoutStatistiquesJoueurB(Joueur joueurListe,Joueur gagnant, boolean egalite) {
        Joueur j=joueurListe;
        if (j.equals(joueurB)) {
            if (egalite) {
                j.setNombreNul(j.getNombreNul() + 1);
            } else {
                if (joueurB.equals(gagnant)) {
                    j.setNombreGagne((j.getNombreGagne()) + 1);
                } else {
                    j.setNombrePerdu((j.getNombrePerdu()) + 1);
                }
            }
            j.setNombrePartie((j.getNombrePartie()) + 1);
        }
        return j;
    }

    private Joueur ajoutStatistiquesJoueurA(Joueur joueurListe,Joueur gagnant, boolean egalite) {
        Joueur j=joueurListe;
        if (j.equals(joueurA)) {
            if (egalite) {
                j.setNombreNul(j.getNombreNul() + 1);
            } else {
                if (joueurA.equals(gagnant)) {
                    j.setNombreGagne((j.getNombreGagne()) + 1);
                } else {
                    j.setNombrePerdu((j.getNombrePerdu()) + 1);
                }
            }
            j.setNombrePartie((j.getNombrePartie()) + 1);
        }
        return j;
    }

    /**
     * Retourne le plateau.
     * @return Retourne le plateau.
     */
    public Plateau getPlateau() {
        return plateau;
    }

}
