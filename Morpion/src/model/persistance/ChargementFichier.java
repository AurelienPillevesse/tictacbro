/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import model.Joueur;

/**
 * Classe qui étends la classe abstraite Chargement, permettant de charger les données depuis un fichier.
 */
public class ChargementFichier extends Chargement {

    /**
     * Permet de charger un fichier de sauvegarder. Si il n'y a de fichier de sauvegarde trouvé, il en crée un.
     * @return La liste des joueurs contenue dans le fichier.
     */
    @Override
    public List<Joueur> charger() {
        FileInputStream fis = null;
        fis = OuvertureFileInputStream(fis);
        try (ObjectInputStream i = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                joueurs.add((Joueur) i.readObject());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return joueurs;
    }

    private FileInputStream OuvertureFileInputStream(FileInputStream fis) {
        try {
            fis = new FileInputStream("FichierSauvegarder");
        } catch (FileNotFoundException ex) {
        }
        return fis;
    }
}
