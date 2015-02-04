package presentation;

import boundary.TestDAO;
import entity.Utilisateur;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@ViewScoped
public class Test implements Serializable{
    @Inject
    TestDAO testDAO;
    private Utilisateur u;
    private long idUser;

    public TestDAO getTestDAO() {
        return testDAO;
    }

    public void setTestDAO(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }
    
    /***************************************************************************/    
    @PostConstruct
    public void onInit(){
        this.u = new Utilisateur();
    }
    
    public String redirect(long id){
        // On récupère l'id de l'utilisateur à modifier
        this.idUser = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser"));
        // On met dans l'URL l'id récupéré
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("idUser", this.idUser);
        return "test2?faces-redirect=true";
    }
    
    public void getFromFlash(ComponentSystemEvent e){
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        this.idUser = ((long) flash.get("idUser"));
    }
    
    public void loadUser(){
        Utilisateur user = testDAO.find(idUser);
        this.u = user;
        FacesMessage msgError = new FacesMessage(this.u.toString());
        FacesContext.getCurrentInstance().addMessage(null, msgError);
    }
    
}
