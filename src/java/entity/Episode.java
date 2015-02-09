package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private boolean isRead;
    @ManyToOne
    private Cours cours;

    /****************/
    /* Constructors */
    /****************/
    public Episode() {
    }

    public Episode(String nomEpisode, String fichierVideo, Date dateEpisode, boolean isRead) {
        this.nomEpisode = nomEpisode;
        this.fichierVideo = fichierVideo;
        this.dateEpisode = dateEpisode;
        this.isRead = isRead;
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

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    /*************/
    /* Functions */
    /*************/
    @Override
    public String toString() {
        return "Episode{" + "idEpisode=" + idEpisode + ", nomEpisode=" + nomEpisode + ", fichierVideo=" + fichierVideo + ", isRead=" + isRead + ", cours=" + cours + '}';
    }
}
