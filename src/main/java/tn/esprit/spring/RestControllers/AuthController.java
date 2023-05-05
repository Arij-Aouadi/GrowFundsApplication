package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Role;
import tn.esprit.spring.DAO.Entities.TypeRole;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.RoleRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.Services.Interfaces.IUserService;
import tn.esprit.spring.payload.request.LoginRequest;
import tn.esprit.spring.payload.request.SignupRequest;
import tn.esprit.spring.payload.response.JwtResponse;
import tn.esprit.spring.payload.response.MessageResponse;
import tn.esprit.spring.security.jwt.JwtUtils;
import tn.esprit.spring.security.services.UserDetailsImpl;


import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@Getter
@Setter
@RestController
@AllArgsConstructor
@Slf4j
public class AuthController {


    @Autowired
    UserRepository userRepository;
    private IUserService iUserService;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        // Ajouter le log suivant pour vérifier si l'utilisateur est correctement authentifié
        log.debug("L'utilisateur {} est correctement authentifié", loginRequest.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Ajouter le log suivant pour vérifier les rôles récupérés à partir de l'utilisateur connecté
        log.debug("Rôles récupérés à partir de l'utilisateur connecté : {}", roles);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }




    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if (userRepository.existsUserByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsUserByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        User u = new User(user.getUsername(),
                user.getEmail(),
                encoder.encode(user.getPassword()));

        u.setUsername(user.getUsername());
        u.setPassword(encoder.encode(user.getPassword()));
        u.setCin(user.getCin());
        u.setCreatedDate(new Date());
        u.setAddress(user.getAddress());
        u.setEmail(user.getEmail());
        u.setSalary(user.getSalary());
        //u.setRole(userRepository.getRoleByIdd(user.getId()));


        //Create new user's account

        /*User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        User user1 = user;
        user1.setUsername(signUpRequest.getUsername());
        user1.setPassword(encoder.encode(signUpRequest.getPassword()));
        user1.setCin(signUpRequest.getCin());
        user1.setCreatedDate(new Date());
        user1.setAddress(signUpRequest.getAddress());
        user1.setEmail(signUpRequest.getEmail());
        user1.setSalary(signUpRequest.getSalary());
       // user1.setRole(userRepository.getRoleByIdd(signUpRequest.getId()));;*/

        Set<Role> strRoles = user.getRole();

        Set<Role> roles = new HashSet<>();

        if (roles == null) {
            Role userRole = roleRepository.findByTypeRole(TypeRole.CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else if (strRoles!=null){
            strRoles.forEach(role -> {
                switch (role.getTypeRole().toString()) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByTypeRole(TypeRole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "AGENT":
                        Role modRole = roleRepository.findByTypeRole(TypeRole.AGENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByTypeRole(TypeRole.CLIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }


        user.setRole(roles);

        userRepository.save(u);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!" + user.getUsername()));
    }


}