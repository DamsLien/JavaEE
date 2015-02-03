package security;

import entity.Utilisateur;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class Cryptage {
    
    public String cryptage(Utilisateur utilisateur) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(utilisateur.getMdp().getBytes());
        
        byte[] bytes = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0x0ff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
}
