package czar.seguidino.api.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import czar.seguidino.core.dtos.TokenDto;

@RestController
@RequestMapping(value="/api/users", produces={"application/xml", "application/json"})
public class UserController {

    @RequestMapping(value="/login", method=POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDto login(@RequestBody final UserLogin login, HttpServletResponse response) throws UnauthorizedException {
        
        TokenDto token = new TokenDto();
        token.setToken("2134724987293479");
        token.setNombre("cesar");
        token.setAdmin(true);
//        try {
//            token = tokenService.createToken(login.getUsername(), login.getPassword());            
//        } catch (Exception ex) {
//            throw new UnauthorizedException(ex.getMessage());    
//        }      
        
        return token;
    }

    public static class UserLogin {
        public String username;
        public String password;

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return this.username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return this.password;
        }
    }
    
}