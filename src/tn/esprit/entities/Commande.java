/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.entities;

import java.util.Date;

/**
 *
 * @author ameni
 */
public class Commande {
    private int id,reference;
private boolean valider;
private String date;

/*jointure produit*/
Commande[] commande;


public Commande(){
    }
    
    public Commande(int id,int reference,boolean valider,String date){
    this.id=id;
    this.reference=reference;
    this.valider=valider;
    this.date=date;
    }
    public Commande(int reference,boolean valider,String date){
    
    this.reference=reference;
    this.valider=valider;
    this.date=date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public boolean isValider() {
        return valider;
    }
 public boolean getValider() {
        return valider;
    }
    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Commande[] getCommande() {
        return commande;
    }

    public void setCommande(Commande[] commande) {
        this.commande = commande;
    }

@Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", reference=" + reference +
                ", Date='" + date+ '\'' +
                ", valider='" + valider + '\'' +
             
                '}';

    }

}
