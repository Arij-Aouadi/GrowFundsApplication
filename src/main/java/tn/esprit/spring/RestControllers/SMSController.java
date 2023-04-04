package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.DAO.Entities.SmsSendRequest;
import tn.esprit.spring.Services.Classes.Smsservice;
@AllArgsConstructor
@RestController
public class SMSController {
    @Autowired
    Smsservice smsservice;
//POST http://localhost:1006/processSMS
    @PostMapping("/processSMS")
    public String processSms(@RequestBody SmsSendRequest smsSendRequest) {


return smsservice.sendSms(smsSendRequest.getDestinationSMSNumber(),smsSendRequest.getSmsMessage());
    }
}
