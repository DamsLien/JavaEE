package presentation;

import boundary.EpisodeDAO;
import entity.Cours;
import entity.Episode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Damien
 */
@ManagedBean
@ViewScoped
public class ListeEpisodes implements Serializable{
    @Inject
    EpisodeDAO episodeDAO;
    private Cours cours;
    private List<Episode> episodes;

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

    public List<Episode> getEpisodes() {
        this.episodes = episodeDAO.findAllByCours(this.cours);
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
    
    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit(){
        this.cours = new Cours();
        this.episodes = new ArrayList<>();
    }
    
    public String imageVideoYouTube(Episode episode){
        int slash = episode.getFichierVideo().lastIndexOf("/")+1;
        return episode.getFichierVideo().substring(slash);
    }

}
