package entity;

import entity.Episode;
import entity.Utilisateur;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-26T19:23:41")
@StaticMetamodel(Cours.class)
public class Cours_ { 

    public static volatile ListAttribute<Cours, Utilisateur> utilisateurs;
    public static volatile SingularAttribute<Cours, String> image;
    public static volatile SingularAttribute<Cours, Double> prix;
    public static volatile SingularAttribute<Cours, String> nomCours;
    public static volatile SingularAttribute<Cours, Long> idCours;
    public static volatile SingularAttribute<Cours, String> description;
    public static volatile ListAttribute<Cours, Episode> episodes;

}