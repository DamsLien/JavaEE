package presentation;

import boundary.MesCoursDAO;
import entity.Cours;
import entity.Episode;
import entity.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class MesCours implements Serializable{
    @Inject
    MesCoursDAO mesCoursDao;
    
    private List<Cours> listeCours;
    private Cours cours;
    @ManagedProperty("#{connexion}")
    private Connexion connexion;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public MesCoursDAO getMesCoursDao() {
        return mesCoursDao;
    }

    public void setMesCoursDao(MesCoursDAO mesCoursDao) {
        this.mesCoursDao = mesCoursDao;
    }

    public List<Cours> getListeCours() {
        return listeCours;
    }

    public void setListeCours(List<Cours> listeCours) {
        this.listeCours = listeCours;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    
    @Override
    public int hashCode(){
        int hash = 7;
        return hash;
    }

    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit() {
        this.listeCours = new ArrayList<>();
        this.cours = new Cours();
    }
    
    public void listMyCourses(){
        this.listeCours = mesCoursDao.findAll(this.connexion.getUtilisateur());
    }
    
    /**
     * Redirection de l'annuaire vers la description du cours
     * @return le chemin de redirection
     */
    public String listeToCours(){
        this.cours.setIdCours(Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCours")));
        return "regarderCours?faces-redirect=true&amp;includeViewParams=true";
    }
    
    public void coursAchete(){
        boolean existe = false;
        listMyCourses();
        
        for(Cours c : this.listeCours){
            if(c.getIdCours() == this.cours.getIdCours()){
                existe = true;
                break;
            }
        }
        
        if(!existe){
            try{ 
                String ctx = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(ctx + "/home.xhtml"); }
            catch(Exception exc){ exc.printStackTrace(); }
        }
    }
    
    /**
     * Chargement des données relatives au cours
     */
    public void loadCours(){
        this.cours = mesCoursDao.find(this.cours.getIdCours());
    }
    
    /**
     * Indique dans la base si l'épisode a été visionné
     * @param episode 
     */
    public void episodeVu(Episode episode){
        Utilisateur utilisateur = connexion.getUtilisateur();
        List<Utilisateur> listU = new ArrayList<>();
        listU.add(utilisateur);
        episode.setListeUtilisateurs(listU);
        System.out.println("------------ " + episode);
    }
    
}
