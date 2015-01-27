package entity;

import entity.Utilisateur;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-27T13:23:33")
@StaticMetamodel(CreditCard.class)
public class CreditCard_ { 

    public static volatile SingularAttribute<CreditCard, Integer> code;
    public static volatile SingularAttribute<CreditCard, Integer> cryptogramme;
    public static volatile SingularAttribute<CreditCard, Utilisateur> utilisateur;
    public static volatile SingularAttribute<CreditCard, Long> idCarte;

}