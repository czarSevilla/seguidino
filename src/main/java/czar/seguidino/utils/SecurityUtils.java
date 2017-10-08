package czar.seguidino.utils;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import czar.seguidino.entities.User;

public class SecurityUtils {

    private SecurityUtils() {
        
    }
    
    public static User getUser(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            return (User) ((UsernamePasswordAuthenticationToken) principal).getDetails();
        }
        return null;
    }
    
    public static Long getIdUser(Principal principal) {
        User user = getUser(principal);
        if (user != null) {
            return user.getIdUser();
        }
        return null;
    }
}
