package boundary;

import entity.Cours;
import entity.Cours_;
import entity.Episode;
import entity.Episode_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
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
public class EpisodeDAO {
    @PersistenceContext
    EntityManager em;
    
    /**
     * Trouver un épisode selon identifiant
     * @param id identifiant de l'épisode
     * @return épisode en entier
     */
    public Episode find(long id){
        return this.em.find(Episode.class, id);
    }
    
    
    public Cours findCours(Episode e){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Episode.class);
        Root<Episode> root = cq.from(Episode.class);
        Join<Episode, Cours> join = root.join(Episode_.cours);
        Predicate condition = cb.and(
                                    cb.equal(root.get(Episode_.cours).get(Cours_.idCours), join.get(Cours_.idCours)), 
                                    cb.equal(root.get(Episode_.idEpisode), e.getIdEpisode())
                              );
        cq.where(condition);
        cq.select(join);
        TypedQuery<Cours> tq = this.em.createQuery(cq);
        
        try{ 
            return tq.getSingleResult();
        }
        catch(NoResultException nre){
            return null;
        }
    }
    
    /**
     * Trouver tous les épisodes d'un cours donné
     * @param cours cours por lequel on recherche les épidoses
     * @return une liste d'épisode ou null s'il n'y a pas d'épisode
     */
    public List<Episode> findAllByCours(Cours cours){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Episode.class);
        Root<Episode> root = cq.from(Episode.class);
        Predicate condition = cb.equal(root.get(Episode_.cours).get(Cours_.idCours), cours.getIdCours());
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
    
    /**
     * Ajouter un épisode dans la base
     * @param e Episode à ajouter dans la base
     */
    public void addEpisode(Episode e){
        this.em.persist(e);
    }
    
    /**
     * Suppression d'un épisode
     * @param e Episode à supprimer de la base
     */
    public boolean deleteEpisode(Episode e){
        try{
            // Merge avant remove, sinon problème
            this.em.remove(this.em.merge(e));
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    /**
     * Modification d'un épisode uniquement si le nom et le lien vidéo sont renseignés
     * @param episode épisode à modifier
     * @return épisode modifié avec les nouvelles données
     */
    public Episode modifyEpisode(Episode episode){
        Episode e = find(episode.getIdEpisode());
        
        if(e != null){
            e.setNomEpisode(episode.getNomEpisode());
            e.setFichierVideo(episode.getFichierVideo());
        }
        
        return e;
    }
}
