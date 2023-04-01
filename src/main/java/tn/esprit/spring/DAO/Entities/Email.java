package tn.esprit.spring.DAO.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Email {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}