package entity;

import java.io.Serializable;
import java.math.BigInteger;
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
    private String code; // Code de la carte
    private String cryptogramme; // Cryptogramme
    // Plusieurs cartes de crédits peuvent être affecté à un utilisateur
    @ManyToOne
    private Utilisateur utilisateur;

    /****************/
    /* Constructors */
    /****************/
    public CreditCard() {
    }

    public CreditCard(String code, String cryptogramme) {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCryptogramme() {
        return cryptogramme;
    }

    public void setCryptogramme(String cryptogramme) {
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
