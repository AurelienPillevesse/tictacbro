/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 * Classe abstraite permettant la mise en place du patron Itérateur.
 */
public abstract class Iterateur {
    protected List<Case> cases;
    public abstract void start();
    public abstract List<Case> next();
    public abstract boolean hasNext();
}
