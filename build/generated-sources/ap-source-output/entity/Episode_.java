package entity;

import entity.Cours;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-01-27T13:42:38")
@StaticMetamodel(Episode.class)
public class Episode_ { 

    public static volatile SingularAttribute<Episode, Long> idEpisode;
    public static volatile SingularAttribute<Episode, String> fichierVideo;
    public static volatile SingularAttribute<Episode, Boolean> isRead;
    public static volatile SingularAttribute<Episode, String> nomEpisode;
    public static volatile SingularAttribute<Episode, Cours> cours;

}