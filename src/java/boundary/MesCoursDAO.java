package boundary;

import entity.Cours;
import entity.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Damien
 */
@Stateless
public class MesCoursDAO {
    @PersistenceContext
    EntityManager em;
    
    /**
     * Retrouver les cours achetés par l'utilisateur
     * @param u utilisateur ayant acheté les cours
     * @return list de cours
     */
    public List<Cours> findAll(Utilisateur u){
        Query query = this.em.createQuery("SELECT c FROM Cours c JOIN c.utilisateurs us WHERE us.idUser = :id");
        query.setParameter("id", u.getIdUser());
        return query.getResultList();
    }
    
    public Cours find(long idCours){
        return this.em.find(Cours.class, idCours);
    }
}
