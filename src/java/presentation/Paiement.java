package presentation;

import boundary.PanierDAO;
import entity.CreditCard;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import security.Cryptage;

/**
 *
 * @author Damien
 */
@ManagedBean
@ViewScoped
public class Paiement implements Serializable{
    @Inject
    PanierDAO panierDAO;
    
    private List<CreditCard> listeCB;
    private CreditCard cb;
    @ManagedProperty("#{connexion}")
    private Connexion connexion;

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

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    
    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit(){
        this.listeCB = new ArrayList<>();
        this.cb = new CreditCard();
    }
    
    /**
     * 1 - Création de la CB si elle n'existe pas encore
     * 2 - Message d'erreur si la CB n'existe pas et qu'on ne veut pas la créer
     * 3 - Message d'erreur si la CB ne correspond pas à l'utilisateur
     * 4 - CB connu et liée à l'utilisateur, on valide
     * @return chemin de redirection selon l'un des cas ci-dessus
     */
    public String validerCB(){
        // Cryptage des données
        Cryptage cryptage = new Cryptage();
        try{ this.cb = cryptage.cryptage(this.cb); }
        catch(NoSuchAlgorithmException e){ e.printStackTrace(); }
        
        CreditCard temp = panierDAO.findCBByInfos(this.cb);
        
        // Carte inconnue
        if(temp == null){
            FacesMessage msgError = new FacesMessage("Cette carte bancaire n'existe pas !");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
            return "";
        }
        
        // Carte connue mais pas la bonne personne
        if(temp.getUtilisateur().getIdUser() != connexion.getUtilisateur().getIdUser()){
            FacesMessage msgError = new FacesMessage("Cette carte bancaire ne vous appartient pas !");
            FacesContext.getCurrentInstance().addMessage(null, msgError);
            return "";
        }
        
        // Carte connu et bon utilisateur
        return "valider?faces-redirect=true";
    }
    
}
