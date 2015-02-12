package security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Damien
 */
@RequestScoped
public class PatternEmail {
    private final Pattern VALID_EMAIL_ADDRESS = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

    public boolean patternEmail(String email){
        Matcher matcher = VALID_EMAIL_ADDRESS.matcher(email);
        return matcher.find();
    }
}
