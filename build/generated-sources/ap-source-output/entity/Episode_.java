package entity;

import entity.Cours;
import entity.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-02-13T16:46:12")
@StaticMetamodel(Episode.class)
public class Episode_ { 

    public static volatile SingularAttribute<Episode, Date> dateEpisode;
    public static volatile SingularAttribute<Episode, Long> idEpisode;
    public static volatile SingularAttribute<Episode, String> fichierVideo;
    public static volatile SingularAttribute<Episode, String> nomEpisode;
    public static volatile SingularAttribute<Episode, Cours> cours;
    public static volatile ListAttribute<Episode, Utilisateur> listeUtilisateurs;

}