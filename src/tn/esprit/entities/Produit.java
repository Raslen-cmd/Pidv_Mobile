/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.entities;

/**
 *
 * @author ameni
 */
public class Produit {
    
    private int idPdt,stock;
    private String nomPdt,description;
    float prix;
    boolean disponible;
/*jointure abec commande*/
    Produit[] produit;
    
    
    public Produit(){
    }
    
    public Produit(int idPdt,int stock,String nomPdt,String description,float prix,boolean disponible){
    this.idPdt=idPdt;
    this.stock=stock;
    this.nomPdt=nomPdt;
    this.description=description;
    this.prix=prix;
    this.disponible=disponible;
    
    }
     public Produit(int stock,String nomPdt,String description,float prix,boolean disponible){
     this.stock=stock;
    this.nomPdt=nomPdt;
    this.description=description;
    this.prix=prix;
    this.disponible=disponible;
    }

    public int getIdPdt() {
        return idPdt;
    }

    public void setIdPdt(int idPdt) {
        this.idPdt = idPdt;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNomPdt() {
        return nomPdt;
    }

    public void setNomPdt(String nomPdt) {
        this.nomPdt = nomPdt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Produit[] getProduit() {
        return produit;
    }

    public void setProduit(Produit[] produit) {
        this.produit = produit;
    }

    



}
