package presentation;

import boundary.EpisodeDAO;
import entity.Episode;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class DeleteEpisode {
    @Inject
    EpisodeDAO episodeDAO;
    private Episode episode;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public EpisodeDAO getEpisodeDAO() {
        return episodeDAO;
    }

    public void setEpisodeDAO(EpisodeDAO episodeDAO) {
        this.episodeDAO = episodeDAO;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
    
    /*************/
    /* Fonctions */
    /*************/   
    @PostConstruct
    public void onInit(){
        this.episode = new Episode();
    }
    
    /**
     * Suppression d'un épisode en récupérant son ID
     */
    public void doDelete(){
        this.episode.setIdEpisode(Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idEpisode")));
        this.episode = episodeDAO.find(this.episode.getIdEpisode());
        
        System.out.println(this.episode);
        
        // Suppression du cours + message si suppression impossible
        if(!episodeDAO.deleteEpisode(this.episode)){
            FacesMessage msgError = new FacesMessage("Suppression impossible ! Une erreure a été rencontrée");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
}
