package boundary;

import entity.Utilisateur;
import entity.Utilisateur_;
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
public class UtilisateurDAO {
    @PersistenceContext
    EntityManager em;
    
    /**
     * Vérification de connexion
     * @param utilisateur utilisateur essayant de se connecter
     * @return l'utilisateur connecté, null sinon
     */
    public Utilisateur checkLoginPassword(Utilisateur utilisateur){
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<Utilisateur> cq = cb.createQuery(Utilisateur.class);
        Root<Utilisateur> root = cq.from(Utilisateur.class);
        Predicate condition = cb.and(
                                cb.like(root.get(Utilisateur_.login), utilisateur.getLogin()), 
                                cb.like(root.get(Utilisateur_.mdp), utilisateur.getMdp())
                              );
        cq.where(condition);
        cq.select(root);
        TypedQuery<Utilisateur> tq = this.em.createQuery(cq);
        Utilisateur u = null;
        
        try{
            u = tq.getSingleResult();
        }catch(Exception e){}
        
        return u;
    }
    
    /**
     * On recherche si le login saisi lors de l'inscription existe déjà.
     * @param login login a rechercher dans la base de donnees
     * @return true si le login est trouve, false sinon
     */
    public Utilisateur findByLogin(String login){
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<Utilisateur> cq = cb.createQuery(Utilisateur.class);
        Root<Utilisateur> root = cq.from(Utilisateur.class);
        Predicate condition = cb.like(root.get(Utilisateur_.login), login);
        cq.where(condition);
        cq.select(root);
        TypedQuery<Utilisateur> tq = this.em.createQuery(cq);
        try{
            Utilisateur u = tq.getSingleResult();
            return u;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    /**
     * Recherche l'existence d'una adresse email
     * @param email email dont on veut savoir si elle existe
     * @return l'utilisateur ayant la dite adresse mail
     */
    public Utilisateur findByEmail(String email){
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<Utilisateur> cq = cb.createQuery(Utilisateur.class);
        Root<Utilisateur> root = cq.from(Utilisateur.class);
        Predicate condition = cb.like(root.get(Utilisateur_.mail), email);
        cq.where(condition);
        cq.select(root);
        TypedQuery<Utilisateur> tq = this.em.createQuery(cq);
        try{
            Utilisateur u = tq.getSingleResult();
            return u;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
    /**
     * Inscription dans la base d'un utilisateur
     * @param user utilisateur à inscrire dans la base
     */
    public void inscription(Utilisateur user){
        try{ this.em.persist(user); }
        catch(Exception e){ e.printStackTrace(); }
    }
}
