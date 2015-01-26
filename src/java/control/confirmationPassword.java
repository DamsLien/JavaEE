package control;

import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Damien
 */
@FacesValidator(value = "confirmationPassword")
public class confirmationPassword implements Validator{
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        UIInput composantMdp = (UIInput) component.getAttributes().get("composantMdp");
        String motDePasse = (String) composantMdp.getValue();
        String confirmation = (String) value;
        
        if(confirmation != null && !confirmation.equals(motDePasse)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Le mot de passe et la confirmation doivent être identiques", null));    
        }
    }

}
