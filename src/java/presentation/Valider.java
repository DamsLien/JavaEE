package presentation;

import boundary.PanierDAO;
import entity.Cours;
import entity.Utilisateur;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Damien
 */
@ManagedBean
@ViewScoped
public class Valider {
    @Inject
    PanierDAO panierDAO;
    
    @ManagedProperty("#{connexion}")
    private Connexion connexion;
    @ManagedProperty("#{panier}")
    private Panier panier;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public PanierDAO getPanierDAO() {
        return panierDAO;
    }

    public void setPanierDAO(PanierDAO panierDAO) {
        this.panierDAO = panierDAO;
    }

    public Connexion getConnexion() {
        return connexion;
    }

    public void setConnexion(Connexion connexion) {
        this.connexion = connexion;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
    
    /*************/
    /* Fonctions */
    /*************/
    /**
     * Ajouter tout les cours présents dans le panier à la base
     * L'utilisateur peut donc les consulter à son bon vouloir
     * @return chemin de redirection vers les cours detenus par l'utilisateur
     */
    public String doAddPanier(){
        List<Cours> listeCours = panier.getListeCours();
        Utilisateur utilisateur = connexion.getUtilisateur();
        
        utilisateur.setListeCours(listeCours);
        // panierDAO.addAllPanier(utilisateur);
        
        return "panier?faces-redirect=true";
    }
    
}
