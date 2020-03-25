package pl.infoshare.webinar.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtLoginSuccessHandler implements AuthenticationSuccessHandler {

    public static final String SECRET_KEY = "amdaijsdn2j1npdakdsadnu38b3hdnj32iubd832dncbu329e3287sqd89ds9a9uq39423";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        var token = generateJwtToken();
        response.addHeader(HttpHeaders.AUTHORIZATION, token);
        response.getWriter().write(token);
        response.getWriter().flush();
        response.getWriter().close();
    }

    public String generateJwtToken() {
        return Jwts.builder()
                .setIssuer("Maciek")
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                .claim("message", "Very private message in jwt token")
                .compact();
    }
}
