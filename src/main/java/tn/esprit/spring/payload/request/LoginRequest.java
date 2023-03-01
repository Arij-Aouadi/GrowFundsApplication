package tn.esprit.spring.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginRequest implements Serializable {
   // @NotBlanc
private String username;
    //@NotBlank
    private String password;


}
