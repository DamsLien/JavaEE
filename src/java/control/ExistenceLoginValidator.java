/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import boundary.UtilisateurDAO;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class ExistenceLoginValidator implements Validator{
    @Inject
    private UtilisateurDAO utilisateurDAO;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String login = (String) value;
        
        try{
            if(utilisateurDAO.findByLogin(login) != null){
                throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, "Impossible de choisir ce login", null ) );
            }
        }
        catch(Exception e){ 
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage( component.getClientId( facesContext ), message );
        }
    }
}
