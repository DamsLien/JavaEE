package presentation;

import boundary.UtilisateurDAO;
import entity.Utilisateur;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import security.Cryptage;

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
    private boolean isLogged;
    
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

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
    /**
     * Connexion au site
     * avec cryptage du mot de passe afin de le comparer à la base de données
     */
    public void logIn(){
        Cryptage cryptage = new Cryptage();
        try{ utilisateur.setMdp(cryptage.cryptage(utilisateur)); }
        catch(NoSuchAlgorithmException e){ }
        utilisateur = utilisateurDAO.checkLoginPassword(utilisateur);
        
        if(utilisateur != null){
            isLogged = true;
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
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        setIsLogged(false);
        try{
            String msg = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(msg + "/index.xhtml");
       }
        catch(IOException e){}
    }
    
    /**
     * Vérification qu'un utilisateur est bien log pour lui permettre l'accès aux pages
     * @param event 
     */
    public void verificationLogin(ComponentSystemEvent event){
        if(!isLogged){
            try{
                String msg = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(msg + "/index.xhtml");
            }
            catch(IOException e){}
        }
    }
    
}
