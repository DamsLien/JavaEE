package presentation;

import boundary.CoursDAO;
import entity.Cours;
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
        // this.liste = coursDAO.findAll();
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
    
    /**
     * Convertir le point d'un double en sigle €
     * On supprime également les 0 inutiles après la virgule
     * @param prix prix à formater
     * @return le prix avec le sigle € et retiré des 0 inutiles
     */
    public String sigleEuro(double prix){
        String valeur = "Gratuit";
        if(prix > 0.00){ 
            valeur = Double.toString(prix).replace(".", "€"); 
            int euro = valeur.indexOf("€");
            if(valeur.substring(euro).equals("€0")){ valeur = valeur.substring(0, euro+1); }
        }
        
        return valeur;
    }

    /**
     * Listing de tous les cours
     * Utile pour la gestion de ces derniers (partie Admin.)
     */
    public void listAllCours(){
        this.liste = coursDAO.findAll();
    }

    /**
     * Listing des cours ayant au moins un épisode
     * Permet de ne pas afficher les cours sans épisodes
     */
    public void listWithEpisode(){
        List<Cours> alc = coursDAO.findAll();
        
        for(Cours c : alc){
            if(coursDAO.nbEpisode(c.getIdCours()) > 0){
                this.liste.add(c);
            }
        }
    }
}
