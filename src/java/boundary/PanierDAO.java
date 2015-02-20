package boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damien
 */
@Stateless
public class PanierDAO {
    @PersistenceContext
    EntityManager em;
    
    
}
