package pl.infoshare.webinar.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class JwtAuthenticatedUser extends AbstractAuthenticationToken {

    private ApplicationUser applicationUser;

    public JwtAuthenticatedUser(ApplicationUser applicationUser) {
        super(Collections.emptyList());
        this.applicationUser = applicationUser;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return applicationUser;
    }
}
