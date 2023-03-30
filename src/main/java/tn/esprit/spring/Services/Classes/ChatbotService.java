package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.ChatBot;
import tn.esprit.spring.DAO.Repositories.ChatbotRepository;
import tn.esprit.spring.Services.Interfaces.IChatbotService;

import java.util.List;

@AllArgsConstructor
@Service
public class ChatbotService implements IChatbotService {
    private ChatbotRepository chatbotRepository;
   @Override
    public List<ChatBot> selectall() {
        return chatbotRepository.findAll();
    }

    @Override
    public List<ChatBot> addAll(List<ChatBot> list) {
        return chatbotRepository.saveAll(list);
    }

}
