package pl.infoshare.webinar.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    public JwtAuthenticationToken(String token) {
        super(Collections.emptyList());
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
