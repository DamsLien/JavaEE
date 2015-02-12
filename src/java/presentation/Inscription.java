package presentation;

import boundary.UtilisateurDAO;
import entity.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import security.Cryptage;
import security.PatternEmail;

/**
 *
 * @author Damien
 */
@Named
@SessionScoped
public class Inscription implements Serializable{
    @Inject
    UtilisateurDAO utilisateurDAO;
    
    private Utilisateur utilisateur;
    private String mdpConfirmation;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public UtilisateurDAO getUtilisateurDAO() {
        return utilisateurDAO;
    }

    public void setUtilisateurDAO(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMdpConfirmation() {
        return mdpConfirmation;
    }

    public void setMdpConfirmation(String mdpConfirmation) {
        this.mdpConfirmation = mdpConfirmation;
    }
    
    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit(){
        utilisateur = new Utilisateur();
    }
    
    public void encryptage(){
        String secure = utilisateur.getMdp();
        try{ secure = new Cryptage().cryptage(utilisateur); }
        catch(NoSuchAlgorithmException e){ e.printStackTrace(); }
        utilisateur.setMdp(secure);
    }
    
    public void inscription(){
        // Mise en forme de l'identité de l'utilisateur
        String nom = utilisateur.getNom().toUpperCase();
        String prenom = utilisateur.getPrenom().toLowerCase();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        
        // Le login n'existe pas, il peut être choisi
        if(utilisateurDAO.findByLogin(utilisateur.getLogin()) == null){
            // Mise à jour des valeurs
            encryptage();
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setIsAdmin(false);
            // Insertion dans la base
            utilisateurDAO.inscription(utilisateur);
                
            try{ FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml"); }
            catch(IOException e){}
        }
        
    }
    
}
