package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Entities.ChatBot;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;

public interface IUserService {
    List<User> selectall ();
    User add(User user);
    User edit(User user);
    List<User> selectAll();
    User selectById(Long id);
    void deleteById(Long id);
    void delete(User user);
    void deleteAll(List<User> list);

    User getById(long id);

    User getCurrentUser();
    User assignAccountToUser(long userId, int accountNum);

    public User getConnectedUser();
}
