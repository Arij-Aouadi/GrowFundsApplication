package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.DAO.Entities.ChatBot;
import tn.esprit.spring.DAO.Entities.Role;
import tn.esprit.spring.DAO.Entities.TypeRole;
import tn.esprit.spring.DAO.Entities.User;

import tn.esprit.spring.Services.Interfaces.IChatbotService;
import tn.esprit.spring.Services.Interfaces.IUserService;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/Packs")
public class ChatBotController {

    private IChatbotService iChatBotservice;
    private IUserService userService;

   @PostMapping("/chatbot")
    public String handleChatBotRequest(@RequestParam("msg") String msg) {


        List<ChatBot> chatbotList = iChatBotservice.selectall();

        String bestResponse = null;
        int bestMatchCount = 0;

        for (ChatBot chatbotResponse : chatbotList) {
            int matchCount = 0;
            String[] responseWords = chatbotResponse.getResponses().split(" ");
            for (String word : msg.split(" ")) {

                for (String responseWord : responseWords) {
                    System.out.println(responseWord);
                    if (word.equalsIgnoreCase(responseWord)) {
                        matchCount++;
                    }
                }
            }
            if (matchCount > bestMatchCount) {
                bestMatchCount = matchCount;
                bestResponse = chatbotResponse.getResponses();
            }
        }
        if (bestResponse != null) {return bestResponse;}
        else {
            bestResponse = "Sorry, I didn't understand that , would you like to send a message to the admin ?.";
            TypeRole adminRole = TypeRole.AGENT;

            List<User> userList = userService.selectall();

            for (User user : userList) {
                Set<Role> mesroles =  user.getRole();
                for (Role roles : mesroles){

                if (roles.getTypeRole()== adminRole) {
                    String a= user.getNewQuestions();

                    user.setNewQuestions("/ new question"+a+msg);
                    userService.add(user);


                }}
            }
            return "i don't have the answer , i'll notify the admin";
        }

    }


}
