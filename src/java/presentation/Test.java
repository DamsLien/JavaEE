package presentation;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Damien
 */
@ManagedBean
@ViewScoped
public class Test implements Serializable{
    private boolean newCB;

    public boolean isNewCB() {
        return newCB;
    }

    public void setNewCB(boolean newCB) {
        this.newCB = newCB;
    }
    
    @PostConstruct
    public void onInit(){
        this.newCB = false;
    }
    
    public String tryCheckBox(){
        System.out.println("Résultat: " + this.newCB);
        return "test2?faces-redirect=true";
    }
}
