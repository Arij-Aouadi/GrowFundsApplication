package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.ChatBot;

public interface ChatbotRepository extends JpaRepository <ChatBot,Integer> {

}
