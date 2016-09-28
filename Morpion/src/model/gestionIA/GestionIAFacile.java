/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionIA;

import java.util.Random;
import javafx.scene.control.Button;
import model.Case;
import model.Plateau;

/**
 * Gestionnaire de l'IA facile qui étends de la classe abstraite Gestionnaire.
 */
public class GestionIAFacile extends Gestionnaire{
    
    private static Random random = new Random();
    private Case testCase;
    private int nombreCase;
    private Boolean done;
    
    /**
     * Permet à l'IA facile de jouer son tour.
     * @param plateau Le plateau de jeu.
     */
    @Override
    public void jouer(Plateau plateau) {
        done = false;
        nombreCase = plateau.getCases().size();
        while(done != true) {
            int x = random.nextInt(3);
            int y = random.nextInt(3);
            if(plateau.egalite()) {
                done = true;
            }
            testPlacerSurUneCase(plateau, x, y);
        }
    }
    
    private void testPlacerSurUneCase(Plateau plateau, int x, int y) {
        for (int i = 0; i < nombreCase; i++) {
            testCase = plateau.getCases().get(i);
            if(testCase.getX() == x && testCase.getY() == y && testCase.getJoueur() == -1) {
                plateau.placerSurCase(x, y, 2);
                done = true;
                break;
            }
        }
    }
    
}
