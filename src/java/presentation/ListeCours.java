package presentation;

import boundary.CoursDAO;
import entity.Cours;
import java.io.Serializable;
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
public class ListeCours implements Serializable{
    @Inject
    CoursDAO coursDAO;
    private List<Cours> liste;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public CoursDAO getCoursDAO() {
        return coursDAO;
    }

    public void setCoursDAO(CoursDAO coursDAO) {
        this.coursDAO = coursDAO;
    }

    // On récupère la liste des cours présents dans la BdD
    public List<Cours> getListe() {
        this.liste = coursDAO.findAll();
        return liste;
    }

    public void setListe(List<Cours> liste) {
        this.liste = liste;
    }
    
    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit(){
        this.liste = new ArrayList();
    }
    
    /**
     * On compte le nombre d'épisodes présents pour le cours ayant l'id sécifié
     * @param idCours identifiant du cours
     * @return nombre d'épisodes concernant le-dit cours
     */
    public long nbEpisodes(long idCours){
        return coursDAO.nbEpisode(idCours);
    }
    
    /**
     * On troncature la description si celle-ci dépasse les 1
     * @param description
     * @return 
     */
    public String troncateDescription(String description){
        if(description.length() >= 210){
            return description.substring(0, 200) + " (...)";
        }
        return description;
    }

}
