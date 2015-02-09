package security;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@ApplicationScoped
public class CheminAbsolu {
    private String cheminAbsolu;

    public String getCheminAbsolu() {
        return cheminAbsolu;
    }

    public void setCheminAbsolu(String cheminAbsolu) {
        this.cheminAbsolu = cheminAbsolu;
    }
    
    public void absolutePath(){
        this.cheminAbsolu = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
    }
}
