package boundary;

import entity.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damien
 */
@Stateless
public class TestDAO {
    @PersistenceContext
    EntityManager em;
    
    public Utilisateur find(long id){
        return this.em.find(Utilisateur.class, id);
    }
}
