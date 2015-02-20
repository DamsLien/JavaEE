package presentation;

import boundary.CoursDAO;
import boundary.PanierDAO;
import entity.Cours;
import entity.Episode;
import java.util.ArrayList;
import java.util.List;
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
public class DescriptionCours {
    @Inject
    PanierDAO panierDAO;
    @Inject
    CoursDAO coursDAO;
    
    private Cours cours;
    private List<Episode> episodes;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public PanierDAO getPanierDAO() {
        return panierDAO;
    }

    public void setPanierDAO(PanierDAO panierDAO) {
        this.panierDAO = panierDAO;
    }

    public CoursDAO getCoursDAO() {
        return coursDAO;
    }

    public void setCoursDAO(CoursDAO coursDAO) {
        this.coursDAO = coursDAO;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<Episode> getEpisodes() {
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
    
    /**
     * Redirection de l'annuaire vers la description du cours
     * @return le chemin de redirection
     */
    public String annuaireToDescription(){
        this.cours.setIdCours(Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCours")));
        return "descriptionCours?faces-redirect=true&amp;includeViewParams=true";
    }
    
    /**
     * Chargement des données relatives au cours
     */
    public void loadCours(){
        this.cours = coursDAO.find(this.cours.getIdCours());
    }
    
    /**
     * Convertir le point d'un double en sigle €
     * On supprime également les 0 inutiles après la virgule
     * @return le prix avec le sigle € et retiré des 0 inutiles
     */
    public String sigleEuro(){
        String valeur = "Gratuit";
        if(this.cours.getPrix() > 0.00){ 
            valeur = Double.toString(this.cours.getPrix()).replace(".", "€"); 
            int euro = valeur.indexOf("€");
            if(valeur.substring(euro).equals("€0")){ valeur = valeur.substring(0, euro+1); }
        }
        
        return valeur;
    }
}
