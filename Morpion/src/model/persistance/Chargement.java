/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistance;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Joueur;

/**
 * Classe abstraite concernant le chargement des données.
 */
public abstract class Chargement {
    protected List<Joueur> joueurs = new ArrayList<>();
    public abstract List<Joueur> charger();
}
