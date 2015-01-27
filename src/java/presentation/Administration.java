package presentation;

import boundary.AdminDAO;
import entity.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class Administration implements Serializable{
    @Inject
    AdminDAO adminDAO;
    private List<Utilisateur> listUsers = new ArrayList<>();

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public List<Utilisateur> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<Utilisateur> listUsers) {
        this.listUsers = listUsers;
    }
    
}
