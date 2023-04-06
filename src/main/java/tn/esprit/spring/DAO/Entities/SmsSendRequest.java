package tn.esprit.spring.DAO.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsSendRequest {
    private String destinationSMSNumber;
    private String smsMessage;
}
