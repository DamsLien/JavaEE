package presentation;

import boundary.CoursDAO;
import boundary.MesCoursDAO;
import entity.Cours;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
    @Inject
    MesCoursDAO mesCoursDAO;
    
    private List<Cours> liste;
    @ManagedProperty("#{connexion}")
    private Connexion connexion;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public CoursDAO getCoursDAO() {
        return coursDAO;
    }

    public void setCoursDAO(CoursDAO coursDAO) {
        this.coursDAO = coursDAO;
    }

    public MesCoursDAO getMesCoursDAO() {
        return mesCoursDAO;
    }

    public void setMesCoursDAO(MesCoursDAO mesCoursDAO) {
        this.mesCoursDAO = mesCoursDAO;
    }

    // On récupère la liste des cours présents dans la BdD
    public List<Cours> getListe() {
        // this.liste = coursDAO.findAll();
        return liste;
    }

    public void setListe(List<Cours> liste) {
        this.liste = liste;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
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
    public void listInCatalogue(){
        List<Cours> alc = coursDAO.findAll();
        List<Cours> alc2 = mesCoursDAO.findAll(connexion.getUtilisateur());
        
        for(Cours c : alc){
            for(Cours c2 : alc2){
                if(coursDAO.nbEpisode(c.getIdCours()) > 0 && c.getIdCours() != c2.getIdCours()){
                    this.liste.add(c);
                }
            }
        }
    }
}
