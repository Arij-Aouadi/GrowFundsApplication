package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }
    @GetMapping("/user")
    @PreAuthorize(" hasRole('CLIENT') ")
    public String userAccess() {
        return "Client Content.";
    }

    @GetMapping("/Agent")
    @PreAuthorize("hasRole('AGENT')")
    public String AgentAccess() {
        return "Agent Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

}
