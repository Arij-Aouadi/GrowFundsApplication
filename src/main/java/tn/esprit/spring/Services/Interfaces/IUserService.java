package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.ChatBot;
import tn.esprit.spring.DAO.Entities.User;

import javax.mail.MessagingException;
import java.util.List;

public interface IUserService {
    List<User> selectall ();
    User add(User user);
    User edit(User user);
    List<User> selectAll();
    List<User> getAgents ();
    List<User> getAdmins();
    List<User> getClients();
    User selectById(Long id);
    void deleteById(Long id);
    void delete(User user);
    void deleteAll(List<User> list);

    User getById(long id);
    void affecterRoleToUser(Long idRole, Long id);

    //int sendAttachmentEmail(String ReciverEmail) throws MessagingException;

    User getCurrentUser();
}
