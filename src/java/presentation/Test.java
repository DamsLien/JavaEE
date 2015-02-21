package presentation;

import entity.CreditCard;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import security.Cryptage;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class Test implements Serializable{
    private CreditCard cb1;
    private CreditCard cb2;

    public CreditCard getCb1(){
        return cb1;
    }

    public void setCb1(CreditCard cb1) {
        this.cb1 = cb1;
    }

    public CreditCard getCb2() {
        return cb2;
    }

    /*********************/
    /* Getters & Setters */
    /*********************/
    public void setCb2(CreditCard cb2) {
        this.cb2 = cb2;
    }

    /*************/
    /* Functions */
    /*************/
    @PostConstruct
    public void onInit() {
        this.cb1 = new CreditCard();
        this.cb2 = new CreditCard();
    }
    
    
    public void encrypte(){
        Cryptage cryptage = new Cryptage();
        
        this.cb2.setCode("1234123412341234");
        this.cb2.setCryptogramme("111");
        System.out.println("2) " + this.cb2);
        try{ this.cb2 = cryptage.cryptage(cb2); }
        catch(NoSuchAlgorithmException e){ e.printStackTrace(); }
        System.out.println("2) " + this.cb2);
    }

}
