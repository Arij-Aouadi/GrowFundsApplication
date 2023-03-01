package tn.esprit.spring.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {

        this.message = message;
    }



    }











