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
    
    public List<Utilisateur> findAllUsers(){
        return this.em.createNamedQuery("findAllUsers", Utilisateur.class).getResultList();
    }
}
