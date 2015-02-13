package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Pauline J. & Damien L.
 */
@Entity
@NamedQuery(name = "findAllUsers", query = "SELECT u FROM Utilisateur u ORDER BY u.nom ASC, u.prenom ASC")
public class Utilisateur implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(length=40)
    @NotNull
    @Size(min = 2)
    private String nom;
    @Column(length=20)
    @NotNull
    @Size(min = 2)
    private String prenom;
    @Column(length = 20)
    @NotNull
    private String login;
    @Column(length=100)
    @Pattern( regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Merci de saisir une adresse mail valide" )
    private String mail;
    @NotNull
    private String mdp;
    // Un utilisateur peut avoir plusieurs cartes de crédit
    // Si un utilisateur est supprimé, alors ces cartes de crédits aussi (CascadeType.ALL)
    // Si on veut créer une carte sans utilisateur, impossible (orphanRemoval)    
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="utilisateur")
    private List<CreditCard> cb;
    @ManyToMany
    private List<Cours> listeCours;
    @ManyToMany
    private List<Episode> listeEpisodes;
    @NotNull
    private boolean isAdmin;

    /****************/
    /* Constructors */
    /****************/
    public Utilisateur() {
    }

    public Utilisateur(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }

    public Utilisateur(String nom, String prenom, String login, String mail, String mdp, boolean isAdmin) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mail = mail;
        this.mdp = mdp;
        this.isAdmin = isAdmin;
    }
    
    public Utilisateur(String nom, String prenom, String login, String mail, String mdp, List<CreditCard> cb, boolean isAdmin) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mail = mail;
        this.mdp = mdp;
        this.cb = cb;
        this.isAdmin = isAdmin;
    }

    /*********************/
    /* Getters & Setters */
    /*********************/
    public long getIdUser() {
        return idUser;
    }
    
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public List<CreditCard> getCb() {
        return cb;
    }

    public void setCb(List<CreditCard> cb) {
        this.cb = cb;
    }
    
    public List<Cours> getListeCours() {
        return listeCours;
    }

    public void setListeCours(List<Cours> listeCours) {
        this.listeCours = listeCours;
    }

    public List<Episode> getListeEpisodes() {
        return listeEpisodes;
    }

    public void setListeEpisodes(List<Episode> listeEpisodes) {
        this.listeEpisodes = listeEpisodes;
    }
    
    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /*************/
    /* Functions */
    /*************/
    @Override
    public String toString() {
        return "Utilisateur{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", mail=" + mail + ", mdp=" + mdp + ", cb=" + cb + ", listeCours=" + listeCours + ", isAdmin=" + isAdmin + '}';
    }    
    
}
