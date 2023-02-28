package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.DAO.Repositories.RoleRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;

@RestController
@AllArgsConstructor
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

}
