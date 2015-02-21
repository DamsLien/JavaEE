package presentation;

import boundary.MesCoursDAO;
import entity.Cours;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    
    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit(){
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
    
    /**
     * Chargement des données relatives au cours
     */
    public void loadCours(){
        this.cours = mesCoursDao.find(this.cours.getIdCours());
    }
    
}
