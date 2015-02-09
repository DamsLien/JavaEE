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

}
