package entity;

import entity.Cours;
import entity.CreditCard;
import entity.Episode;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-02-23T18:14:17")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile SingularAttribute<Utilisateur, Long> idUser;
    public static volatile SingularAttribute<Utilisateur, String> mail;
    public static volatile ListAttribute<Utilisateur, Cours> listeCours;
    public static volatile SingularAttribute<Utilisateur, String> mdp;
    public static volatile ListAttribute<Utilisateur, Episode> listeEpisodes;
    public static volatile SingularAttribute<Utilisateur, Boolean> isAdmin;
    public static volatile SingularAttribute<Utilisateur, String> login;
    public static volatile SingularAttribute<Utilisateur, String> nom;
    public static volatile SingularAttribute<Utilisateur, String> prenom;
    public static volatile ListAttribute<Utilisateur, CreditCard> cb;

}