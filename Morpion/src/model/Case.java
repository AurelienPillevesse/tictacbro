/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Classe représant une case avec ses informations : sa valeur X (abscisse), sa valeur Y (ordonnée) et
 * la valeur du joueur qui est positionné dessus.
 */
public class Case {
    private int x;
    private int y;
    private int joueur;
    
    /**
     * Constructeur d'une case avec son abscisse et son ordonné avec l'initialisation de 
     * la valeur du joueur à -1 qui signifie qu'il n'y a personne dessus.
     * @param x Un entier qui représente l'abscisse de la case.
     * @param y Un entier qui représente l'ordonnée de la case.
     */
    public Case (int x, int y) {
        this.x = x;
        this.y = y;
        joueur = -1;
    }

    /**
     * Retourne l'abscisse de la case
     * @return Un entier qui représente l'abscisse de la case.
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne l'ordonnée de la case.
     * @return Un entier qui représente l'ordonnée de la case.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Mets à jour le joueur sur la case.
     * @param joueur La nouvelle valeur du joueur sur la case.
     */
    public void setJoueur(int joueur) {
        this.joueur=joueur;
    }
    
    /**
     * Retourne la valeur du joueur sur la case.
     * @return Un entier qui correspond au joueur sur la case.
     */
    public int getJoueur(){
        return joueur;
    }
}
