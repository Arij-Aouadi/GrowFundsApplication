package tn.esprit.spring.Services.Classes;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.Transactions;
import tn.esprit.spring.DAO.Entities.TypeRole;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.TransactionRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.Services.Interfaces.ISmsservice;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;


@Service
@Slf4j

public class Smsservice implements ISmsservice {
    private UserRepository userRepository;
    private TransactionRepository transactionRepository;
    // identifiants Twilio
   public static final String ACCOUNT_SID = "AC6b640e69cdcbba98231cd8490684790e";
    public static final String AUTH_TOKEN = "77a1c436856df7e4aecf2411d4917bf2";
    public static final String OUTGOING_SMS_NUMBER = "+15854886519";

    @PostConstruct
    private void setup() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public String sendSms(String smsNumber, String smsMessage) {
        Message message = Message.creator(
                new PhoneNumber(smsNumber),
                new PhoneNumber(OUTGOING_SMS_NUMBER),
                smsMessage).create();

        return message.getStatus().toString();

    }


  @Scheduled(fixedRate = 86400000)
    @Override
    public void sendSmsReminders() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getRole().equals(TypeRole.CLIENT)) {
                String smsMessage = "Dear " + user.getUsername() + "  has been confirmed. Thank you for your business!";

                sendSms("+21697928950", smsMessage);
            }
        }
    }
}






