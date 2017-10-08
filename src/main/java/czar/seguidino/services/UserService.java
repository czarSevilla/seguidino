package czar.seguidino.services;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;

import czar.seguidino.entities.User;

public interface UserService extends UserDetailsService {
    void saveUser(User user, Set<String> authorities);
}