package presentation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.servlet.http.Part;

/**
 *
 * @author Damien
 */
public class UploadFile {
    private final String pathDest = "C:\\Users\\Damien\\Documents\\Miage\\M2\\JEE\\ProjetJEE\\web\\images\\cours\\";
    
    /**
    * On copie l'image sélectionner vers le dossier adéquat
    * Cette copie permet de garder l'image sur le serveur.
    * L'utilisateur l'ayant chargée de son PC, s'il la supprime de celui-ci, il n'y pas d'impacts
    * @throws IOException 
    */
    public String upload(Part part) throws IOException{
        File dest = File.createTempFile("cours", ".jpg");
        
        try(InputStream input = part.getInputStream()){
            dest = new File(pathDest, dest.getName());
            Files.copy(input, dest.toPath());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return dest.getName();
    }
}
