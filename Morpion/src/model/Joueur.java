/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Classe contenant les informations sur les joueurs : leur nom, prénom, couleur, 
 * nombre de parties jouées, gagnées, perdues, égalitées et le ratio de victoires sur défaites.
 */
public class Joueur implements Externalizable {
    private static final long serialVersionUID = 1L;
    
    private final StringProperty nom = new SimpleStringProperty(this,"nom");
        public String getNom() {return nom.get();}
        public void setNom(String value) {nom.set(value);}
        public StringProperty nomProperty() {return nom;}
        
    
    private final StringProperty prenom = new SimpleStringProperty();
        public String getPrenom() {return prenom.get();}
        public void setPrenom(String value) {prenom.set(value);}
        public StringProperty prenomProperty() {return prenom;}
    
    private final StringProperty couleur = new SimpleStringProperty();
        public String getCouleur() {return couleur.get();}
        public void setCouleur(String value) {couleur.set(value);}
        public StringProperty couleurProperty() {return couleur;}
        
    private final IntegerProperty nombrePartie = new SimpleIntegerProperty();
        public int getNombrePartie() {return nombrePartie.get();}
        public void setNombrePartie(int value) {nombrePartie.set(value);}
        public IntegerProperty nombrePartieProperty() {return nombrePartie;}
    
    private final IntegerProperty nombreGagne = new SimpleIntegerProperty();
        public int getNombreGagne() {return nombreGagne.get();}
        public void setNombreGagne(int value) {nombreGagne.set(value);}
        public IntegerProperty nombreGagneProperty() {return nombreGagne;}
    
    private final IntegerProperty nombrePerdu = new SimpleIntegerProperty();
        public int getNombrePerdu() {return nombrePerdu.get();}
        public void setNombrePerdu(int value) {nombrePerdu.set(value);}
        public IntegerProperty nombrePerduProperty() {return nombrePerdu;}
    
    private final IntegerProperty nombreNul = new SimpleIntegerProperty();
        public int getNombreNul() {return nombreNul.get();}
        public void setNombreNul(int value) {nombreNul.set(value);}
        public IntegerProperty nombreNulProperty() {return nombreNul;}
    
    private final FloatProperty ratio = new SimpleFloatProperty();
        public float getRatio() {
            if(getNombrePerdu()==0){
                if(getNombreGagne()==0){
                    return 1;
                } else {
                    return getNombreGagne();
                }
            } else {
                return (getNombreGagne()/(float)getNombrePerdu());
            }
        }
        public void setRatio(float value) {ratio.set(value);}
        public FloatProperty ratioProperty() {return ratio;}
    
    public Joueur () {
        setNom("Nom");
        setPrenom("Prenom");
        setCouleur("Blue");
        setNombrePartie(0);
        setNombreGagne(0);
        setNombrePerdu(0);
        setNombreNul(0);
        setRatio(1);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Joueur other = (Joueur) obj;
        if (!Objects.equals(this.getNom(), other.getNom())) {
            return false;
        }
        if (!Objects.equals(this.getPrenom(), other.getPrenom())) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return getPrenom();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getNom());
        out.writeObject(getPrenom());
        out.writeObject(getCouleur());
        out.writeInt(getNombrePartie());
        out.writeInt(getNombreGagne());
        out.writeInt(getNombrePerdu());
        out.writeInt(getNombreNul());
        out.writeFloat(getRatio());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setNom((String) in.readObject());
        setPrenom((String) in.readObject());
        setCouleur((String) in.readObject());
        setNombrePartie(in.readInt());
        setNombreGagne(in.readInt());
        setNombrePerdu(in.readInt());
        setNombreNul(in.readInt());
        setRatio(in.readFloat());
    }
    
    
    
    
    
    
    
    
    
}
