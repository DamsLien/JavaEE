package control;

import entity.Utilisateur;
import entity.Utilisateur_;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Damien
 */
@FacesValidator("ExistenceEmailValidator")
public class ExistenceEmailValidator implements Validator{    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
               
    }
}
