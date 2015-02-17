package presentation;

import boundary.CoursDAO;
import entity.Cours;
import java.util.Date;
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
public class UpdateCours {
    @Inject
    CoursDAO coursDAO;
    private Cours cours;

    /*********************/
    /* Getters & Setters */
    /*********************/
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
    
    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit(){
        this.cours = new Cours();
    }
    
    public String listeToModify(){
        long idCours = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCours"));
        this.cours.setIdCours(idCours);
        
        return "modifyCours?faces-redirect=true&amp;includeViewParams=true";
    }
    
    public void loadCours(){
       this.cours = coursDAO.find(this.cours.getIdCours());
    }
    
    public String doModify(){
        // On modifie le cours
        this.cours.setDateAjout(new Date());
        coursDAO.updateCours(this.cours);
        
        
        return "listCourses?faces-redirect=true";
    }
}
