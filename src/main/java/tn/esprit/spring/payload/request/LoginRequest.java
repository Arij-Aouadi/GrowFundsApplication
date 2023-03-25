package tn.esprit.spring.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class LoginRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;


}
