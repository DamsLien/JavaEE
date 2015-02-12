package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.servlet.http.Part;

/**
 *
 * @author Pauline J. & Damien L.
 */
@Entity
@NamedQuery(name = "findAllCourses", query = "SELECT c FROM Cours c ORDER BY c.nomCours ASC")
public class Cours implements Serializable{
    @Id
    @GeneratedValue
    private long idCours;
    @Column(length = 30)
    private String nomCours;
    @Column (length = 200)
    private String description;
    private String image; // URL de l'image
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAjout;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="cours")
    private List<Episode> episodes;
    @ManyToMany(mappedBy="listeCours")
    private List<Utilisateur> utilisateurs;
    @Column(precision = 2)
    private double prix;
    
    @Transient
    private Part part;

    /****************/
    /* Constructors */
    /****************/
    public Cours() {
    }

    public Cours(String nomCours, String description, String image, Date dateAjout, List<Episode> episodes, double prix) {
        this.nomCours = nomCours;
        this.description = description;
        this.image = image;
        this.dateAjout = dateAjout;
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

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    
    /*************/
    /* Functions */
    /*************/
    @Override
    public String toString() {
        return "Cours{" + "idCours=" + idCours + ", description=" + description + ", image=" + image + ", episodes=" + episodes + ", prix=" + prix + '}';
    }
    
    
    
}
