package control;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author Damien
 */
@FacesValidator("FileUploadValidator")
public class FileUpdateValidator implements Validator{
    // Liste des images acceptées
    private String typeMIM[] = {"image/bmp", "image/gif", "image/jpeg", "image/png"};
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        boolean typeOk = false;
        
        if(value != null){
            Part part = (Part) value;

            // 1. Validation du nom du fichier
            String fileName = part.getSubmittedFileName();
            System.out.println("----- Validator fileName: " + fileName);
            if (fileName.length() == 0) {
                FacesMessage message = new FacesMessage("Nom de fichier invalide !");
                throw new ValidatorException(message);
            } else if (fileName.length() > 50) {
                FacesMessage message = new FacesMessage("Nom du fichier trop long !");
                throw new ValidatorException(message);
            }

            // 2. Validation du type de fichier (uniquement des images)
            for(String s : typeMIM){
                if (s.equals(part.getContentType())) {
                    typeOk = true;
                    break;
                }
            }
            if(!typeOk){
                FacesMessage message = new FacesMessage("Type de fichier invalide !");
                throw new ValidatorException(message);
            }

            // 3. Validation de la taille de l'image (2 Mo max)
            if (part.getSize() > 2000000) {
                FacesMessage message = new FacesMessage("Fichier trop lourd !");
                throw new ValidatorException(message);
            }
        }
    }    
}
