package czar.seguidino.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import czar.seguidino.dtos.OptionDto;
import czar.seguidino.entities.User;
import czar.seguidino.repositories.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/principal")
    OptionDto principal(Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        return new OptionDto(user.getIdUser(), user.getName(), user.getAvatar(), false);
    }
}
