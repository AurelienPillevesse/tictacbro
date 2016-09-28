/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.persistance;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import model.Joueur;

/**
 * Classe qui étends la classe abstraite Sauvegarde, permettant de sauvegarder les données dans un fichier.
 */
public class SauvegardeFichier extends Sauvegarde {

    /**
     * Permet de sauvegarder la liste des joueurs dans un fichier.
     * @param joueurs La liste des joueurs qui sera enregistrée.
     */
    @Override
    public void sauvegarder(List<Joueur> joueurs) {
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("FichierSauvegarder")) ){
            for (Joueur j : joueurs) {
                o.writeObject(j);
            }
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
