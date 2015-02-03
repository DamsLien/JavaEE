package presentation;

import boundary.AdminDAO;
import entity.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class Administration implements Serializable{
    @Inject
    AdminDAO adminDAO;
    private Utilisateur utilisateur;
    private List<Utilisateur> listUsers = new ArrayList<>();

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

      
    public List<Utilisateur> getListUsers() {
        listUsers = adminDAO.findAllUsers();
        return listUsers;
    }

    public void setListUsers(List<Utilisateur> listUsers) {
        this.listUsers = listUsers;
    }
    
    @PostConstruct
    public void onInit(){
        utilisateur = new Utilisateur();
    }
    
    /**
     * On cherche à obtenir le chemin absolu du projet afin de pouvoir afficher
     * les pages correctement
     * @return le chemin absolu
     */
    public String absolutePath(){
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
    }
    
    /**
     * Suppression d'un utilisateur
     */
    public void doDelete(){
        utilisateur.setIdUser(Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser")));
        
        if(adminDAO.deleteUser(utilisateur.getIdUser())){
            FacesMessage msgError = new FacesMessage("L'utilisateur a bien été supprimé !");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
        else{
            FacesMessage msgError = new FacesMessage("Une erreure est survenue. Suppression avortée !");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
    
}
