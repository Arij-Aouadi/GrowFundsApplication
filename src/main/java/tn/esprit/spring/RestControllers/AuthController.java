package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Role;
import tn.esprit.spring.DAO.Entities.TypeRole;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.RoleRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.payload.request.LoginRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@Getter
@Setter
@RequestMapping("/api/auth")
@RestController
@AllArgsConstructor
public class AuthController {



    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    /*@Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;*/

    /*@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

      Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


    }
*/
   /* @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }*/

        // Create new user's account

    /*User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));*/

        //Set<String> strRoles = signUpRequest.getRole();
        //Set<Role> roles = new HashSet<>();

        /*if(role  == null) {
            Role userRole = roleRepository.findByTypeRole(TypeRole.CLIENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByTypeRole(TypeRole.Admin)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
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

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }*/
}
