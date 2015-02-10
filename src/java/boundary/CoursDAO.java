package boundary;

import entity.Cours;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Damien
 */
@Stateless
public class CoursDAO {
    @PersistenceContext
    EntityManager em;
    
    /**
     * Trouver un cours dans la BdD selon son ID
     * @param idCours
     * @return le cours correspondant à l'ID
     */
    public Cours find(long idCours){
        return this.em.find(Cours.class, idCours);
    }
    
    /**
     * Afficher tous les cours présents dans la BdD
     * @return une liste de cours
     */
    public List<Cours> findAll(){
        return this.em.createNamedQuery("findAllCourses", Cours.class).getResultList();
    }
    
    /**
     * Ajout d'un cours dans la BdD
     * @param c 
     */
    public void addCours(Cours c){
        this.em.persist(c);
    }
    
    /**
     * Suppression du cours
     * @param idCours id permettant d'identifier le cours à supprimer
     * @return true si le cours est supprimé, false sinon
     */
    public boolean deleteCours(long idCours){
        Cours cours = this.find(idCours);
        Path pathDelete = Paths.get("C:\\Users\\Damien\\Documents\\Miage\\M2\\JEE\\ProjetJEE\\web\\images\\cours\\" + cours.getImage());
        
        if(cours != null){
            // Suppression de l'image (pas besoin d'encombrer le dossier)
            try{ Files.delete(pathDelete); }
            catch(IOException e){ e.printStackTrace(); }
            // Suppression du cours
            this.em.remove(cours);
            return true;
        }
        return false;
    }
}
