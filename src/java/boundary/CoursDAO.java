package boundary;

import entity.Cours;
import entity.Cours_;
import entity.Episode;
import entity.Episode_;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import presentation.UploadFile;

/**
 *
 * @author Damien
 */
@Stateless
public class CoursDAO {
    @PersistenceContext
    EntityManager em;
    
    private final String cheminImg = "C:\\Users\\Damien\\Documents\\Miage\\M2\\JEE\\ProjetJEE\\web\\images\\cours\\";
    
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
     * Compte le nombre d'épisodes présent pour le curs ayant l'id renseigné
     * @param idCours ID du cours dont on veut connaitre le nombre d'épisodes
     * @return le nombre d'épisodes
     */
    public long nbEpisode(long idCours){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Episode> e = cq.from(Episode.class);
        Predicate predicat = cb.equal(e.get(Episode_.cours).get(Cours_.idCours), idCours);
        cq.where(predicat);
        cq.select(cb.count(e));
        
        return em.createQuery(cq).getSingleResult();
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
        Path pathDelete = Paths.get(cheminImg + cours.getImage());
        
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
    
    public Cours updateCours(Cours cours){
        Cours c = find(cours.getIdCours());
        if(c != null){
            // Maj du nom du cours s'il est changé
            if(!cours.getNomCours().equals(c.getNomCours())){
                c.setNomCours(cours.getNomCours());
            }
            // Maj de la description si elle est changé
            if(!cours.getDescription().equals(c.getDescription())){
                c.setDescription(cours.getDescription());
            }
            // Maj du prix s'il est changé
            if(cours.getPrix() != c.getPrix()){
                c.setPrix(cours.getPrix());
            }
            // Maj dul'image si elle est changé (url differentes et non nulles)
            if(cours.getPart() != null){
                // On supprime l'image
                Path pathDelete = Paths.get(cheminImg + c.getImage());
                try{ Files.delete(pathDelete); }
                catch(IOException e){ e.printStackTrace(); }
                
                // On change le nom de l'image dans la base
                String nomImage = "";
                try{ nomImage = new UploadFile().upload(cours.getPart()); }
                catch(IOException e){ e.printStackTrace(); }
                c.setImage(nomImage);
            }
            c.setDateAjout(cours.getDateAjout());
        }
        
        return c;
    }
}
