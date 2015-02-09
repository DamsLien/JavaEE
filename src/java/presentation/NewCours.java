package presentation;

import boundary.CoursDAO;
import entity.Cours;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class NewCours {
    @Inject
    CoursDAO coursDAO;
    private Cours cours;
    private Part part;
    private final String pathDest = "C:\\Users\\Damien\\Documents\\Miage\\M2\\JEE\\ProjetJEE\\web\\images\\cours\\";

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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    
    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit(){
        this.cours = new Cours();
        this.cours.setPrix(0);
    }
    
    /**
     * On copie l'image sélectionner vers le dossier adéquat
     * Cette copie permet de garder l'image sur le serveur.
     * L'utilisateur l'ayant chargée de son PC, s'il la supprime de celui-ci, il n'y pas d'impacts
     * @throws IOException 
     */
    public String upload() throws IOException{
        File dest = File.createTempFile("cours", ".jpg");
        
        try(InputStream input = part.getInputStream()){
            dest = new File(pathDest, dest.getName());
            Files.copy(input, dest.toPath());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return dest.getName();
    }
    
    /**
     * On ajoute le nouveau cours dans la BdD
     * L'image choisit est copiée dans le bon dossier
     * @return le lien de redirection
     */
    public String doAjouter(){
        try{ 
            this.cours.setImage(this.upload()); 
            this.cours.setDateAjout(new Date());
            
            coursDAO.addCours(this.cours);
        }
        catch(IOException e){ 
            FacesMessage msgError = new FacesMessage("Une erreure est survenue. Le cours n'a pu être ajouté !");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
        
        return "listCourses?faces-redirect=true";
    }
}
