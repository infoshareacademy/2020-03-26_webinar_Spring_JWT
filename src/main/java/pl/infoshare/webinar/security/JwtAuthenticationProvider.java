package pl.infoshare.webinar.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.infoshare.webinar.security.model.ApplicationUser;
import pl.infoshare.webinar.security.model.JwtAuthenticationToken;
import pl.infoshare.webinar.security.model.JwtAuthenticatedUser;

import java.nio.charset.StandardCharsets;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var tokenHolder = (JwtAuthenticationToken) authentication;
        var user = extractUserFromJwt(tokenHolder);

        return new JwtAuthenticatedUser(user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }

    private ApplicationUser extractUserFromJwt(JwtAuthenticationToken jwtAuthenticationToken) {
        var jwtToken = Jwts.parserBuilder()
                .setSigningKey(JwtLoginSuccessHandler.SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(jwtAuthenticationToken.getToken());
        var username = jwtToken.getBody().getIssuer();
        var message = jwtToken.getBody().get("message", String.class);

        return new ApplicationUser(username, message);
    }
}
