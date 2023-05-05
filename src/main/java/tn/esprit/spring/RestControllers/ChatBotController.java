package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.*;

import tn.esprit.spring.Services.Interfaces.IChatbotService;
import tn.esprit.spring.Services.Interfaces.IChatgptService;
import tn.esprit.spring.Services.Interfaces.IUserService;
import tn.esprit.spring.openai.OutputDto;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class ChatBotController {

    private IChatbotService iChatBotservice;
    private IUserService userService;
    @Autowired
    private IChatgptService chatgptService;


    @GetMapping("/admin/livechat/{question}")
    public String summarizeComplaint(@PathVariable String question) {
        OutputDto out = null;
        try {
            out = chatgptService.sendPrompt("we are  a bank assisstance service for a bank called 'GrowFunds' and our website is 'www.growfunds.com' try to play the role of a bank agent that answer for client question. Answer this is the question : " + question);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return out.getAnswer();
    }

/*
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
*/

}
