package presentation;

import boundary.CoursDAO;
import entity.Cours;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class DeleteCours implements Serializable{
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
        cours = new Cours();
    }
    
    public void doDelete(){
        long id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCours"));
        this.cours.setIdCours(id);
        
        // Suppression du cours + message si suppression impossible
        if(!coursDAO.deleteCours(id)){
            FacesMessage msgError = new FacesMessage("Suppression impossible ! Une erreure a été rencontrée");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
}
