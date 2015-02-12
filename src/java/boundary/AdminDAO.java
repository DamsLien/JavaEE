package boundary;

import entity.Utilisateur;
import entity.Utilisateur_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Damien
 */
@Stateless
public class AdminDAO {
    @PersistenceContext
    EntityManager em;
    
    /**
     * Détection d'un utilisateur dans la BdD grâce à son id
     * @param idUtilisateur id à rechercher
     * @return objet Utilisateur ayant pour ID l'idUtilisateur
     */
    public Utilisateur find(long idUtilisateur){
        return this.em.find(Utilisateur.class, idUtilisateur);
    }
    
    /**
     * Recherche tous les utilisateurs inscrits dans la base
     * @return liste des utilisateurs
     */
    public List<Utilisateur> findAllUsers(){
        return this.em.createNamedQuery("findAllUsers", Utilisateur.class).getResultList();
    }
    
    /**
     * Suppression d'un utilisateur (partie Admin.)
     * @param idUtilisateur id referençant l'utilisateur
     * @return objet Utilisateur correspond à l'id
     */
    public boolean deleteUser(long idUtilisateur){
        Utilisateur utilisateur = find(idUtilisateur);
        
        if(utilisateur != null){
            this.em.remove(utilisateur);
            return true;
        }
        
        return false;
    }
    
    public Utilisateur updateUser(Utilisateur utilisateur){
        Utilisateur u = find(utilisateur.getIdUser());
        if(u != null){
            u.setNom(utilisateur.getNom());
            u.setPrenom(utilisateur.getPrenom());
            u.setLogin(utilisateur.getLogin());
            u.setMail(utilisateur.getMail());
        }
        
        return u;
    }
}
