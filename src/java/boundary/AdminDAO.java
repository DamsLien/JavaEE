package boundary;

import entity.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
