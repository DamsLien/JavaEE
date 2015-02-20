package control;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Damien
 */
@Named
@RequestScoped
public class DateFrancaise {
    
    public String dateEN_FR(Date date){
        DateFormat shortDateFormatEN = DateFormat.getDateTimeInstance(
        DateFormat.SHORT,
        DateFormat.SHORT, new Locale("FR","fr"));
        
        return shortDateFormatEN.format(date);
    } 
            
            
}
