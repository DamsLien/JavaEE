package boundary;

import entity.Cours;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damien
 */
@Stateless
public class CoursDAO {
    @PersistenceContext
    EntityManager em;
    
    /**
     * Trouver un cours dans la BdD selon son ID
     * @param idCours
     * @return le cours correspondant à l'ID
     */
    public Cours find(long idCours){
        return this.em.find(Cours.class, idCours);
    }
    
    /**
     * Afficher tous les cours présents dans la BdD
     * @return une liste de cours
     */
    public List<Cours> findAll(){
        return this.em.createNamedQuery("findAllCourses", Cours.class).getResultList();
    }
    
    /**
     * Ajout d'un cours dans la BdD
     * @param c 
     */
    public void addCours(Cours c){
        this.em.persist(c);
    }
}
