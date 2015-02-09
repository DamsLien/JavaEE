package presentation;

import com.sun.javafx.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class Test implements Serializable{
    private Part part;

    /*********************/
    /* Getters & Setters */
    /*********************/
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    
    /*************/
    /* Functions */
    /*************/
    @PostConstruct
    public void onInit(){
        this.part = null;
    }
    
    public void upload() throws IOException{
        String pathDest = "C:\\Users\\Damien\\Documents\\Miage\\M2\\JEE\\ProjetJEE\\web\\images\\cours\\";
        File dest2 = File.createTempFile("cours", ".jpg");
        
        try(InputStream input = part.getInputStream()){
            dest2 = new File(pathDest, dest2.getName());
            Files.copy(input, dest2.toPath());
            
            FacesMessage msgError = new FacesMessage(dest2.getName());
            FacesContext.getCurrentInstance().addMessage(null, msgError);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
