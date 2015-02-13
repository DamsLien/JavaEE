package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Pauline J. & Damien L.
 */
@Entity
public class Episode implements Serializable{
    @Id
    @GeneratedValue
    private long idEpisode;
    @Column(length = 30)
    private String nomEpisode;
    private String fichierVideo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEpisode;
    @ManyToOne
    private Cours cours;
    @ManyToMany(mappedBy="listeEpisodes")
    private List<Utilisateur> listeUtilisateurs;
    
    /****************/
    /* Constructors */
    /****************/
    public Episode() {
    }

    public Episode(String nomEpisode, String fichierVideo, Date dateEpisode) {
        this.nomEpisode = nomEpisode;
        this.fichierVideo = fichierVideo;
        this.dateEpisode = dateEpisode;
    }

    /*********************/
    /* Getters & Setters */
    /*********************/
    public long getIdEpisode() {
        return idEpisode;
    }

    public void setIdEpisode(long idEpisode) {
        this.idEpisode = idEpisode;
    }

    public String getNomEpisode() {
        return nomEpisode;
    }

    public void setNomEpisode(String nomEpisode) {
        this.nomEpisode = nomEpisode;
    }

    public String getFichierVideo() {
        return fichierVideo;
    }

    public void setFichierVideo(String fichierVideo) {
        this.fichierVideo = fichierVideo;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Date getDateEpisode() {
        return dateEpisode;
    }

    public void setDateEpisode(Date dateEpisode) {
        this.dateEpisode = dateEpisode;
    }

    /*************/
    /* Functions */
    /*************/
    @Override
    public String toString() {
        return "Episode{" + "idEpisode=" + idEpisode + ", nomEpisode=" + nomEpisode + ", fichierVideo=" + fichierVideo + ", cours=" + cours + '}';
    }
}
