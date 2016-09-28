/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe du Plateau de taille 3*3 qui étends de la classe abstraite Plateau avec sa constante pour l'abscisse et 
 * pour l'ordonnée.
 */
public class Plateau3x3 extends Plateau{
    
    private static final int TailleGrilleX = 3;
    private static final int TailleGrilleY = 3;
    
    /**
     * Constructeur d'un plateau.
     */
    public Plateau3x3 (){
        cases = new ArrayList<>();
        for(int i=0;i<TailleGrilleX;i++) {
            for(int j=0;j<TailleGrilleY;j++){
                cases.add(new Case(j,i));
            }
        } 
        iterateur = new IterateurPlateau3x3(cases);
    }

    /**
     * Mets à jour l'entier correspondant au joueur sur une case.
     * @param x Un entier qui correspond à l'abscisse de la case sur laquelle le joueur va être placé.
     * @param y Un entier qui correspong à l'ordonnée de la case sur laquelle le joueur va être placé.
     * @param joueur Un entier qui correspond au joueur qui va être placé sur la case.
     */
    @Override
    public void placerSurCase (int x, int y, int joueur) {
        for(Case c : cases) {
            if(c.getX()== x && c.getY() == y && c.getJoueur() == -1){
                c.setJoueur(joueur);
                caseUtilise++;
            }
        }
    }
    
    /**
     * Permet de savoir si les 9 cases du plateau 3*3 sont utilisées ou non.
     * @return Un booléen, true si les 9 cases sont utilisées.
     */
    @Override
    public Boolean egalite() {
        return caseUtilise==9;
    }

    /**
     * Retourne la liste des cases du plateau.
     * @return La liste des cases du plateau.
     */
    public List<Case> getCases() {
        return cases;
    }

    /**
     * Retourne l'itérateur du plateau.
     * @return Retourne l'itérateur du plateau.
     */
    public Iterateur getIterateur() {
        return iterateur;
    }     
    
}
