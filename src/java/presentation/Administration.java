package presentation;

import boundary.AdminDAO;
import entity.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    long idUtilisateur;
    private List<Utilisateur> listUsers = new ArrayList<>();

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
      
    public List<Utilisateur> getListUsers() {
        listUsers = adminDAO.findAllUsers();
        return listUsers;
    }

    public void setListUsers(List<Utilisateur> listUsers) {
        this.listUsers = listUsers;
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
        idUtilisateur = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser"));
        
        if(adminDAO.deleteUser(idUtilisateur)){
            FacesMessage msgError = new FacesMessage("Supprimé !");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
            /*try{ FacesContext.getCurrentInstance().getExternalContext().redirect("listeUtilisateurs.xhtml"); }
            catch(Exception e){ }*/
        }
        else{
            FacesMessage msgError = new FacesMessage("Pas supprimé !");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
    }
}
