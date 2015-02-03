package presentation;

import boundary.AdminDAO;
import entity.Utilisateur;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
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
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    @PostConstruct
    public void onInit(){
        utilisateur = new Utilisateur();
    }
    
    public String listeToModify(){
        String url = "modifyUser?faces-redirect=true";
        id = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("id", id);
        
        return url;
    }
    
    public void getFromFlash(ComponentSystemEvent e){
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        id = (long) flash.get("id");
        utilisateur = adminDAO.find(id);
    }
    
    public String doModify(){
        return "listeUtilisateurs?faces-redirect=true";
    }
}
