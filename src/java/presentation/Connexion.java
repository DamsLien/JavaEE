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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Damien
 */
@Named
@SessionScoped
public class Connexion implements Serializable{
    @Inject
    UtilisateurDAO utilisateurDAO;
    private Utilisateur utilisateur;
    
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
    
    /**
     * Connexion au site
     */
    public void logIn(){
        utilisateur = utilisateurDAO.checkLoginPassword(utilisateur);
        
        if(utilisateur != null){
            FacesMessage msgError = new FacesMessage("Bonjour " + utilisateur.getPrenom()+ " " + utilisateur.getNom());
            FacesContext.getCurrentInstance().addMessage("msgError", msgError);
            try{
                FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
            }
            catch(IOException e){}
        }
        else{
            FacesMessage msgError = new FacesMessage("Login et/ou  mot de passe erroné(s) !");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
            utilisateur = new Utilisateur();
        }
    }
    
    /**
     * Déconnexion du site et retour à la page de connexion
     */
    public void logOut(){
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        }
        catch(IOException e){}
    }
    
}
