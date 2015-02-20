package presentation;

import boundary.EpisodeDAO;
import entity.Cours;
import entity.Episode;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class UpdateEpisode implements Serializable{
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
     * Récupérationd de l'ID de l'épisode, et redirection vers la page de modification
     * @return le chemin pour accéder à la page de modification
     */
    public String listeToModify(){
        this.episode.setIdEpisode(Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idEpisode")));
        return "modifyEpisode?faces-redirect=true&amp;includeViewParams=true";
    }
    
    /**
     * Lien de retour en cas d'annulation de la modification
     * @return 
     */
    public String backToCours(){
        Cours cours = episodeDAO.findCours(episode);
        return "modifyCours?faces-redirect=true&amp;idCours=" + cours.getIdCours();
    }
    
    /**
     * Charger les données d'un épisode grâce à son ID
     */
    public void loadEpisode(){
        this.episode = episodeDAO.find(this.episode.getIdEpisode());
    }
    
    /**
     * Format du lien YouTube mis en forme
     * @return le lien YouTube tel qu'il doit être dans la base
     */
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
     * Modification de l'épisode (uniquement si le nom et la vidéo sont renseignés)
     * Mais quelque soit le cas, on redirige vers la page de modif du cours
     * @return le lien de redirection
     */
    public String doModify(){
        if(!this.episode.getFichierVideo().equals("") && !this.episode.getNomEpisode().equals("")){
            this.episode.setFichierVideo(youtubeURL());
            System.out.println("--- " + this.episode);
            this.episode = episodeDAO.modifyEpisode(this.episode);
        }
        return backToCours();
    }
}
