package boundary;

import entity.Cours;
import entity.Cours_;
import entity.Episode;
import entity.Episode_;
import entity.Utilisateur;
import entity.Utilisateur_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Cours.class);
        Root<Cours> root = cq.from(Cours.class);
        Join<Cours, Utilisateur> join = root.join(Cours_.utilisateurs);
        Predicate condition = cb.equal(join.get(Utilisateur_.idUser), u.getIdUser());
        cq.where(condition);
        cq.select(root);
        TypedQuery<Cours> tq = this.em.createQuery(cq);
        
        try{ 
            return tq.getResultList();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    /**
     * Retrouver un cours acheté selon son ID
     * @param idCours
     * @return 
     */
    public Cours find(long idCours){
        return this.em.find(Cours.class, idCours);
    }
    
    /**
     * Retrouver l'épisode d'un cours acheté
     * @param idEpisode ID de l'épisode permettant de le retrouver
     * @return Episode trouvé
     */
    public Episode findEpisode(long idEpisode){
        return this.em.find(Episode.class, idEpisode);
    }
    
    /**
     * Ajout le fait que l'épisode soit vu (lu) par l'utilisateur
     * @param u l'utilisateur détenteur du cours où apparait l'épisode
     */
    public void episodeVu(Utilisateur u){
        this.em.merge(u);
        this.em.flush();
    }
    
    /**
     * Recherche la liste des épisodes que l'utilisateur a vu / peut voir
     * @param idU ID de l'utilisateur
     * @return la liste des épisodes pour l'utilisateur
     */
    public List<Episode> verifEpisodeVu(long idU){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Episode.class);
        Root<Episode> root = cq.from(Episode.class);
        Join<Episode, Utilisateur> join = root.join(Episode_.listeUtilisateurs);
        Predicate condition = cb.equal(join.get(Utilisateur_.idUser), idU);
        cq.where(condition);
        cq.select(root);
        TypedQuery<Episode> tq = this.em.createQuery(cq);
        
        try{ 
            return tq.getResultList();
        }
        catch(NoResultException e){
            return null;
        }
    }
}
