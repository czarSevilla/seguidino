package czar.seguidino.services.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import czar.seguidino.entities.User;
import czar.seguidino.repositories.UserRepository;
import czar.seguidino.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user, Set<String> authorities) {
        String encodePass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePass);
        if (authorities != null && authorities.size() > 0) {
            for (String auth : authorities) {
                user.addAuthority(auth);
            }
        }       
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

}
