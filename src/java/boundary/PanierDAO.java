package boundary;

import entity.Cours;
import entity.CreditCard;
import entity.CreditCard_;
import entity.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
public class PanierDAO {
    @PersistenceContext
    EntityManager em;
    
    /**
     * On recherche une carte selon son ID
     * @param idCarte ID de la carte bancaire à retrouver
     * @return la carte bancaire trouvée dans la base
     */
    public CreditCard findCB(long idCarte){
        return this.em.find(CreditCard.class, idCarte);
    }
    
    /**
     * On cherche la carte de crédit selon ses infos
     * @param cc
     * @return 
     */
    public CreditCard findCBByInfos(CreditCard cc){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(CreditCard.class);
        Root<CreditCard> root = cq.from(CreditCard.class);
        Predicate condition = cb.and(
                                cb.equal(root.get(CreditCard_.code), cc.getCode()),
                                cb.equal(root.get(CreditCard_.cryptogramme), cc.getCryptogramme())
                               );
        cq.where(condition);
        cq.select(root);
        TypedQuery<CreditCard> tq = this.em.createQuery(cq);
        
        try{ 
            return tq.getSingleResult();
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    /**
     * Ajouter une carte bancaire à la base de données
     * @param cb Carte Bancaire à ajouter
     */
    public void addCB(CreditCard cb){
        this.em.persist(cb);
    }
    
    /**
     * Ajout des cours achetés par l'utilisateur
     * @param u utilisateur ayant acheté des cours
     */
    public void addAllPanier(Utilisateur u){
        this.em.merge(u);
        this.em.flush();
    }
}
