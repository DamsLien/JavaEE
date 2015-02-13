package presentation;

import boundary.EpisodeDAO;
import entity.Cours;
import entity.Episode;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class ListeEpisodes {
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
    
}
