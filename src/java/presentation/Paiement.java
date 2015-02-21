package presentation;

import boundary.PanierDAO;
import entity.CreditCard;
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
public class Paiement{
    @Inject
    PanierDAO panierDAO;
    
    private List<CreditCard> listeCB;
    private CreditCard cb;
    private boolean createCB;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public PanierDAO getPanierDAO() {
        return panierDAO;
    }

    public void setPanierDAO(PanierDAO panierDAO) {
        this.panierDAO = panierDAO;
    }

    public List<CreditCard> getListeCB() {
        return listeCB;
    }

    public void setListeCB(List<CreditCard> listeCB) {
        this.listeCB = listeCB;
    }

    public CreditCard getCb() {
        return cb;
    }

    public void setCb(CreditCard cb) {
        this.cb = cb;
    }

    public boolean isCreateCB() {
        return createCB;
    }

    public void setCreateCB(boolean createCB) {
        this.createCB = createCB;
    }
    
    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit(){
        this.listeCB = new ArrayList<>();
        this.cb = new CreditCard();
        this.createCB = true;
    }
    
    public void validerCB(){
        CreditCard temp = panierDAO.findCBByInfos(this.cb);
        
        
        
        // Carte inconnue & pas de demande de création
        if(temp == null && !this.createCB){
            FacesMessage msgError = new FacesMessage("Cette carte bancaire n'existe pas !");
            FacesContext.getCurrentInstance().addMessage("form:msgCBExiste", msgError);
        }
        
        if(temp == null && this.createCB){
            
        }
        
        // Carte connue mais pas bon utilisateur
        
        // Carte connu rien à faire
    }
}
