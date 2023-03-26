package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.ChatBot;

import java.util.List;

public interface IChatbotService {
    List<ChatBot> selectall ();
    List<ChatBot> addAll (List<ChatBot> list );

}
