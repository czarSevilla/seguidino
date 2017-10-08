package czar.seguidino.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "sg_users")
public class User implements Serializable, UserDetails {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idUser;
    private String username;
    private String password;
    private String name;
    private String email;
    private boolean active;
    private boolean expired;
    private boolean locked;
    private Set<Authority> authorities;
    private String avatar;
    
    public User() {
        authorities = new HashSet<>();
    }   
    
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Column
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @Column
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Column
    public boolean isExpired() {
        return expired;
    }
    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    
    @Column
    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    @Override
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    public Set<Authority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return !this.locked;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return isAccountNonExpired() 
                && isAccountNonLocked() 
                && isCredentialsNonExpired();
    }
    
    public void addAuthority(String pAuthority) {
        this.authorities.add(new Authority(pAuthority));
    }
    
    @Column
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
