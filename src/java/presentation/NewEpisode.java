package presentation;

import boundary.EpisodeDAO;
import entity.Cours;
import entity.Episode;
import java.util.Date;
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
public class NewEpisode {
    @Inject
    EpisodeDAO episodeDAO;
    
    private Cours cours;
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

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
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
        this.cours = new Cours();
        this.episode = new Episode();
    }
    
    /**
     * Récupération de l'identifiant du cours et redirection vers la page d'ajout d'un épisode
     * @return adresse de redirection
     */
    public String listeToNewEpisode(){
        this.cours.setIdCours(Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCours")));
        
        return "addEpisode?faces-redirect=true&amp;includeViewParams=true";
    }
    
    public String youtubeURL(){
        String startURL = "https://www.youtube.com/embed/";
        int slash = this.episode.getFichierVideo().lastIndexOf("v=")+2;
        int esperluette = this.episode.getFichierVideo().lastIndexOf("&");
        
        if(esperluette > 0){ return startURL + this.episode.getFichierVideo().substring(slash, esperluette); }
        return startURL + this.episode.getFichierVideo().substring(slash); 
    }
    
    /**
     * On récupère l'url pure de YouTube, et on le modifie pour que l'affichage se fasse
     */
    public void formatURL(){
        String url = youtubeURL();
        // start=60&end=90 : Video commencant à 1min et se fini à 1min30 (pour faire un extrait 
        // autoplay=0 : l'utilisateur doit lancer la vidéo
        // controls=0 : reculer/avancer impossible
        this.episode.setFichierVideo(url + "?start=60&amp;end=90&amp;autoplay=0&amp;controls=0&amp;showinfo=0&amp;modestbranding=1&amp;iv_load_policy=3");
    }
    
    /**
     * Ajout d'un episode à un cours.
     * On effecte le cours à l'épisode, ainsi que ça date d'ajout
     * @return le chemin de redirection vers la liste des cours
     */
    public String doAjouter(){
        String url = youtubeURL();
        
        this.episode.setFichierVideo(url);
        this.episode.setCours(this.cours);
        this.episode.setDateEpisode(new Date());
        
        episodeDAO.addEpisode(episode);
        
        return "listCourses?faces-redirect=true";
    }
}
