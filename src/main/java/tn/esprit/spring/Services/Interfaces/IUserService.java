package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.ChatBot;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;

public interface IUserService {
    List<User> selectall ();
    User add(User user);

    User getById(long id);
}
