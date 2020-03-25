package pl.infoshare.webinar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {

    @GetMapping("/api/public")
    public String getPublic() {
        return "It's public";
    }

    @GetMapping("/api/private")
    public String getPrivate() {
        return "I'm so private";
    }
}
