package pl.infoshare.webinar.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.infoshare.webinar.security.model.ApplicationUser;
import pl.infoshare.webinar.security.model.JwtAuthenticatedUser;

@RestController
public class PrivateController {

    @GetMapping("/api/public")
    public String getPublic() {
        return "It's public";
    }

    @GetMapping("/api/private")
    public String getPrivate() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        JwtAuthenticatedUser jwtUser = (JwtAuthenticatedUser) authentication;
        ApplicationUser user = (ApplicationUser) jwtUser.getPrincipal();

        return "I'm so private: " + user.getMessage();
    }
}
