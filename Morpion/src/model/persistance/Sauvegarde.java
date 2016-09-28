/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistance;

import java.util.List;
import javafx.beans.property.ListProperty;
import model.Joueur;

/**
 * Classe abstraite concernant la sauvegarde des données.
 */
public abstract class Sauvegarde {
    public abstract void sauvegarder(List<Joueur> joueurs);
}
