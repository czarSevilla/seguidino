package czar.seguidino.core.dtos;

import java.io.Serializable;

public class TokenDto implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo token.
     */
    private String token;
    /**
     * Atributo nombre.
     */
    private String nombre;
    /**
     * Atributo admin.
     */
    private boolean admin;    

    /**
     * Setter de token.
     * @param pToken Valor a asignar
     */
    public void setToken(final String pToken) {
        this.token = pToken;
    }

    /**
     * Getter de token.
     * @return Valor de token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Setter de nombre.
     * @param pNombre Valor a asignar
     */
    public void setNombre(final String pNombre) {
        this.nombre = pNombre;
    }

    /**
     * Getter de nombre.
     * @return Valor de nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setter de admin.
     * @param pAdmin Valor a asignar
     */
    public void setAdmin(final boolean pAdmin) {
        this.admin = pAdmin;
    }

    /**
     * Getter de admin.
     * @return Valor de admin
     */
    public boolean getAdmin() {
        return this.admin;
    }

    /**
     * Getter IS de admin.
     * @return Valor de admin
     */
    public boolean isAdmin() {
        return this.admin;
    }    
}