package presentation;

import boundary.CoursDAO;
import boundary.PanierDAO;
import entity.Cours;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@SessionScoped
public class Panier implements Serializable{
    @Inject
    PanierDAO panierDAO;
    @Inject
    CoursDAO coursDAO;
    
    private Cours cours;
    private List<Cours> listeCours;
    private double prixTotal;
    
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

    public List<Cours> getListeCours() {
        return listeCours;
    }

    public void setListeCours(List<Cours> listeCours) {
        this.listeCours = listeCours;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
    
    /*************/
    /* Fonctions */
    /*************/     
    @PostConstruct
    public void onInit(){
        this.cours = new Cours();
        this.listeCours = new ArrayList<>();
        this.prixTotal = 0;
    }
    
    /**
     * On ajoute le cours sélectionné au panier
     * et redirige vers la page du panier pour vérifier l'ajout
     * @return le chemin de redirection vers le panier
     */
    public String addToPanier(){
        long idCours = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idCours"));
        this.addInSession(idCours);
        
        return "panier?faces-redirect=true";
    }
    
    /**
     * Ajouter un cours dans la session afin de la valider dans le panier
     */
    public void addInSession(long idCours){
        this.cours = coursDAO.find(idCours);
        this.listeCours.add(this.cours);
        this.prixTotal += this.cours.getPrix();
    }
    
    /**
     * On supprimer un cours qui n'a plus lieu d'être dans le panier
     * L'utilisateur ne le veut plus, il l'a donc enlever
     */
    public void deleteOfSession(long idCours){
        this.cours = coursDAO.find(idCours);
        
        ListIterator<Cours> iterator = this.listeCours.listIterator();
        while(iterator.hasNext()){
            Cours c = iterator.next();
            if(c.getIdCours() == this.cours.getIdCours()){
                iterator.remove();
            }
        }
        
        this.prixTotal -= this.cours.getPrix();
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
     * Vérification de la présence ou non d'un article dans le panier, afin de ne pas le sélectionner à nouveau 
     * et d'acheter plusieurs fois le même 
     * @param idCours 
     * @return true si le cours est déjà dans le panier, false sinon
     */
    public boolean dejaDansPanier(long idCours){
        for(Cours c : this.listeCours){
            if(c.getIdCours() == idCours){
                return true;
            }
        }
        return false;
    }
}
