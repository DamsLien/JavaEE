package security;

import entity.CreditCard;
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
    // Cryptage du mot de passe utilisateur
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
    
    // Cryptage des données de la cb
    public CreditCard cryptage(CreditCard cb) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        MessageDigest md2 = MessageDigest.getInstance("SHA-512");
        
        md.update(cb.getCode().getBytes());
        md2.update(cb.getCryptogramme().getBytes());
        
        byte[] bytes = md.digest();
        byte[] bytes2 = md2.digest();
        
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i=0; i<bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0x0ff) + 0x100, 16).substring(1));
        }
        for(int i=0; i<bytes2.length; i++){
            sb2.append(Integer.toString((bytes2[i] & 0x0ff) + 0x100, 16).substring(1));
        }
        
        cb.setCode(sb.toString());
        cb.setCryptogramme(sb2.toString());
        
        return cb;    
    }
}
