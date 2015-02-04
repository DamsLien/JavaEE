package presentation;

import boundary.AdminDAO;
import entity.Utilisateur;
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
public class UpdateUtilisateur {
    @Inject
    AdminDAO adminDAO;
    private Utilisateur utilisateur;

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    @PostConstruct
    public void onInit(){
        utilisateur = new Utilisateur();
    }
    
    public String listeToModify(){
        long idUser = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser"));
        this.utilisateur.setIdUser(idUser);
        
        return "modifyUser?faces-redirect=true&amp;includeViewParams=true";
    }
    
    public void loadUser(){
        this.utilisateur = adminDAO.find(this.utilisateur.getIdUser());
    }
    
    public String doModify(){
        String prenom = utilisateur.getPrenom().toLowerCase();
        this.utilisateur.setNom(this.utilisateur.getNom().toUpperCase());        
        this.utilisateur.setPrenom(prenom.substring(0, 1).toUpperCase() + prenom.substring(1));
        this.utilisateur = adminDAO.updateUser(utilisateur);
        
        return "listeUtilisateurs?faces-redirect=true";
    }
}
