package entity;

import entity.Utilisateur;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-02-21T19:31:26")
@StaticMetamodel(CreditCard.class)
public class CreditCard_ { 

    public static volatile SingularAttribute<CreditCard, String> code;
    public static volatile SingularAttribute<CreditCard, String> cryptogramme;
    public static volatile SingularAttribute<CreditCard, Utilisateur> utilisateur;
    public static volatile SingularAttribute<CreditCard, Long> idCarte;

}