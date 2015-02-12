package presentation;

import boundary.AdminDAO;
import boundary.UtilisateurDAO;
import entity.Utilisateur;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
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
    @Inject
    UtilisateurDAO utilisateurDAO;
    private Utilisateur utilisateur;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
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
    
    /*************/
    /* Fonctions */
    /*************/
    @PostConstruct
    public void onInit(){
        utilisateur = new Utilisateur();
    }
    
    /**
     * Récupère l'identifiant de l'utilisateur et redirige vers la page de modification
     * @return le chemin de redirection
     */
    public String listeToModify(){
        long idUser = Long.parseLong(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUser"));
        this.utilisateur.setIdUser(idUser);
        
        return "modifyUser?faces-redirect=true&amp;includeViewParams=true";
    }
    
    /**
     * Charge l'un utilisateur lors du chargement de la page de modification de celui-ci
     */
    public void loadUser(){
        this.utilisateur = adminDAO.find(this.utilisateur.getIdUser());
    }
    
    /**
     * Modifie l'utilisateur avec les nouvelles données saisies.
     * @return le chemin de redirection vers la liste des utilisateurs (pou voir le changement)
     */
    public String doModify(){
        String email = this.utilisateur.getMail();
        String login = this.utilisateur.getLogin();
        Utilisateur uEmail = utilisateurDAO.findByEmail(email);
        Utilisateur uLogin = utilisateurDAO.findByLogin(login);

        
        
        if((uEmail != null && uEmail.getIdUser() != this.utilisateur.getIdUser()) || (uLogin != null && uLogin.getIdUser() != this.utilisateur.getIdUser())){
            return "";
        }
        
        String prenom = utilisateur.getPrenom().toLowerCase();
        this.utilisateur.setNom(this.utilisateur.getNom().toUpperCase());        
        this.utilisateur.setPrenom(prenom.substring(0, 1).toUpperCase() + prenom.substring(1));
        this.utilisateur = adminDAO.updateUser(utilisateur);
            
        return "listeUtilisateurs?faces-redirect=true";
    }
}
