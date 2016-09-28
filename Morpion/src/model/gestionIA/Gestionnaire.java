/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.gestionIA;

import javafx.scene.control.Button;
import model.Plateau;

/**
 * Classe abstraite pour la gestion des intelligences artificielles.
 */
public abstract class Gestionnaire {
   
   public abstract void jouer(Plateau plateau);
}
