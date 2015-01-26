package presentation;

import boundary.UtilisateurDAO;
import entity.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

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
    
    @PostConstruct
    public void onInit(){
        utilisateur = new Utilisateur();
    }

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
    
    public void inscription(){ 
        String nom = utilisateur.getNom().toUpperCase();
        String prenom = utilisateur.getPrenom().toLowerCase();
        prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);
        
        // Le login n'existe pas, il peut être choisi
        if(!utilisateurDAO.isFind(utilisateur.getLogin())){
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setIsAdmin(false);
            // Insertion dans la base
            utilisateurDAO.inscription(utilisateur);
            
            try{ FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml"); }
            catch(IOException e){}
        }
        else{ // Le login existe, il faut en choisir un autre
            FacesMessage msgError = new FacesMessage("Ce login existe déjà ! Merci d'en choisir un autre");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
        
    }
    
}
