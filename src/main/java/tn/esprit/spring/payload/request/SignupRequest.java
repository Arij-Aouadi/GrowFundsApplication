package tn.esprit.spring.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.DAO.Entities.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @NotNull
@Size(min = 3, max = 20)
private String username;

    @NotNull
    @Size(max = 50)
    @Email
    private String email;


    @NotNull
    @Size(min = 8, max = 40)
    private String password;
    String cin ;
    List<String> role;



}


