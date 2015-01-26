package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Pauline J. & Damien L.
 */
@Entity
public class Cours implements Serializable{
    @Id
    @GeneratedValue
    private long idCours;
    @Column(length = 30)
    private String nomCours;
    private String description;
    private String image; // URL de l'image
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="cours")
    private List<Episode> episodes;
    @ManyToMany(mappedBy="listeCours")
    private List<Utilisateur> utilisateurs;
    @Column(precision = 2)
    private double prix;

    /****************/
    /* Constructors */
    /****************/
    public Cours() {
    }

    public Cours(String nomCours, String description, String image, List<Episode> episodes, double prix) {
        this.nomCours = nomCours;
        this.description = description;
        this.image = image;
        this.episodes = episodes;
        this.prix = prix;
    }

    /*********************/
    /* Getters & Setters */
    /*********************/
    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }
    
    public long getIdCours() {
        return idCours;
    }

    public void setIdCours(long idCours) {
        this.idCours = idCours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    /*************/
    /* Functions */
    /*************/
    @Override
    public String toString() {
        return "Cours{" + "idCours=" + idCours + ", description=" + description + ", image=" + image + ", episodes=" + episodes + ", prix=" + prix + '}';
    }
    
    
    
}
