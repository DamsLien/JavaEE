package presentation;

import boundary.MesCoursDAO;
import entity.Episode;
import entity.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Damien
 */
@ManagedBean
@ViewScoped
public class LireEpisode implements Serializable{
    @Inject
    MesCoursDAO mesCoursDAO;
    
    @ManagedProperty("#{connexion}")
    private Connexion connexion;
    
    private Episode episode;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public MesCoursDAO getMesCoursDAO() {
        return mesCoursDAO;
    }

    public void setMesCoursDAO(MesCoursDAO mesCoursDAO) {
        this.mesCoursDAO = mesCoursDAO;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
    
    /*************/
    /* FOnctions */
    /*************/
    @PostConstruct
    public void onInit(){
        this.episode = new Episode();
    }
    
    /**
     * Chargement de l'épisode afin d'afficher sa vidéo
     */
    public void loadEpisode(){
        this.episode = mesCoursDAO.findEpisode(this.episode.getIdEpisode());
    }
    
    /**
     * Redirection vers la page qui permet de lire la vidéo elle-m^me
     * On insère également le fait que la vidéo soit visionnée par l'utilisateur
     * @return le chemin de redirection vers la vidéo à regardée
     */
    public String lireEpisode(){
        // Récupération de l'ID de l'épisode
        this.episode.setIdEpisode(Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idEpisode")));
        
        // Ajout dans la base de l'épisode vu
        Utilisateur user = connexion.getUtilisateur();
        List<Episode> listeE = new ArrayList<>();
        listeE.add(this.episode);
        user.setListeEpisodes(listeE);
        mesCoursDAO.episodeVu(user);
        
        return "lireEpisode?faces-redirect=true&amp;includeViewParams=true";
    }
    
    /**
     * Indique si l'épisode passé en paramètre a déjà été vu ou non
     * @param idEpisode ID de l'épisode à vérifier
     * @return true si l'épisode a déjà été vu, false sinon
     */
    public boolean episodeView(long idEpisode){
        Utilisateur user = connexion.getUtilisateur();
        List<Episode> listeE = mesCoursDAO.verifEpisodeVu(user.getIdUser());
        
        for(Episode e : listeE){
            if(e.getIdEpisode() == idEpisode){
                return true;
            }
        }
        
        return false;
    }
}
