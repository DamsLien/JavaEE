package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Pauline J. & Damien L.
 */
@Entity
public class CreditCard implements Serializable{
    @Id
    @GeneratedValue
    private long idCarte; 
    private int code; // Code de la carte
    private int cryptogramme; // Cryptogramme
    // Plusieurs cartes de crédits peuvent être affecté à un utilisateur
    @ManyToOne
    private Utilisateur utilisateur;

    /****************/
    /* Constructors */
    /****************/
    public CreditCard() {
    }

    public CreditCard(int code, int cryptogramme) {
        this.code = code;
        this.cryptogramme = cryptogramme;
    }

    /*********************/
    /* Getters & Setters */
    /*********************/
    public long getIdCarte() {
        return idCarte;
    }

    public void setIdCard(long idCarte) {
        this.idCarte = idCarte;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCryptogramme() {
        return cryptogramme;
    }

    public void setCryptogramme(int cryptogramme) {
        this.cryptogramme = cryptogramme;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /*************/
    /* Functions */
    /*************/
    @Override
    public String toString() {
        return "CreditCard{" + "idCard=" + idCarte + ", code=" + code + ", cryptogramme=" + cryptogramme + ", utilisateur=" + utilisateur + '}';
    }
    
}
