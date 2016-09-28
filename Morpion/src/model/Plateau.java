/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 * Classe abstraite concernant le Plateau.
 */
public abstract class Plateau {
    protected Iterateur iterateur;
    protected List<Case> cases;
    protected int caseUtilise = 0;
    
    public abstract void placerSurCase (int x, int y, int joueur);
    public abstract List<Case> getCases();
    public abstract Boolean egalite();
    public abstract Iterateur getIterateur();
}
