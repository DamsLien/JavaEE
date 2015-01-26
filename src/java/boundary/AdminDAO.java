package boundary;

import entity.Utilisateur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
        try{
            return this.em.createNamedQuery("findAllUsers", Utilisateur.class).getResultList();
        }
        catch(Exception e){
            FacesMessage msgError = new FacesMessage("Il n'y a pas de membre pour votre site !");
            FacesContext.getCurrentInstance().addMessage(null, msgError); 
            
            return new ArrayList<>();
        }
    }
}
