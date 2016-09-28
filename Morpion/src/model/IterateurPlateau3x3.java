/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour l'itérateur d'un plateau 3*3 qui étends de la classe abstraite Itérateur avec la liste des triplés.
 */
public class IterateurPlateau3x3 extends Iterateur {
    private int cpt = 0;
    private int listeTriple[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    private boolean nonTermine = true;
    
    /**
     * Constructeur de l'itérateur pour un plateau 3*3.
     * @param cases Une liste de cases.
     */
    public IterateurPlateau3x3(List<Case> cases) {
        this.cases = cases;
    }
    
    /**
     * Mets à 0 le compteur pour l'itérateur du tableau de triplés et true au booléen qui signifie 
     * que nous nous sommes pas à la fin du tableau.
     */
    public void start(){
        cpt = 0;
        nonTermine = true;
    }
    
    /**
     * Permet le parcours séquentiel du tableau de triple.
     * @return Un tableau de 3 cases contenant un triple.
     */
    @Override
    public List<Case> next() {
        List<Case> caseRetour = new ArrayList<>();
        caseRetour.add(cases.get(listeTriple[cpt][0]));
        caseRetour.add(cases.get(listeTriple[cpt][1]));
        caseRetour.add(cases.get(listeTriple[cpt][2]));
        if(cpt == 7){
            nonTermine = false;
        }
        cpt++;
        return caseRetour;
    }
    
    /**
     * Retourne un booléen informant si il y a encore des triples à parcourir ou non.
     * @return Un boolean, true si il reste des triples à parcourir.
     */
    public boolean hasNext(){
        return nonTermine;
    }
}
