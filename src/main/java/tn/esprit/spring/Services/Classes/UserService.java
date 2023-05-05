package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.Role;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.AccountRepository;
import tn.esprit.spring.DAO.Repositories.RoleRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.Services.Interfaces.IAccountService;
import tn.esprit.spring.Services.Interfaces.IUserService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.SecureRandom;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Service
@NoArgsConstructor

public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    public AccountRepository accountRepository;
    @Override
    public List<User> selectall() {
        return userRepository.findAll();
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }
    @Override
    public User edit(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> selectAll() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public List<User> getAgents() {
        return userRepository.findAgents();
    }

    @Override
    public List<User> getAdmins() {
        return userRepository.findAdmins();
    }

    @Override
    public List<User> getClients() {
        return userRepository.findClients();
    }

    @Override
    public User selectById(Long id) {
        return userRepository.findById(id).get();    }

    @Override
    public void deleteById(Long id) {userRepository.deleteById(id);

    }



    @Override
    public void delete(User user) {

        userRepository.delete(user);

    }

    @Override
    public void deleteAll(List<User> list) {
        userRepository.deleteAll(list);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user= userRepository.findByUsername(currentUserName).get();
            return user;}
        else {
            return null;
        }
    }

    @Override
    public User assignAccountToUser(long userId, int accountNum) {
        Account account= accountRepository.findAccountByAccountNum(accountNum);
        User user=userRepository.findUserById(userId);
        user.getAccountList().add(account);
        account.setUser(user);
        return userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void affecterRoleToUser(Long idRole, Long id) {
        Role r = roleRepository.findById(idRole).get(); //child
        User u = userRepository.findById(id).get(); //parent
        Set<Role> roles = u.getRole(); // get the set of roles for this user
        roles.add(r); // add the new role to the set
        u.setRole(roles); // set the updated set of roles back to the user
        userRepository.save(u);
    }



    public int sendAttachmentEmaill(String receiverEmail, String resetUrl) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        // Generate a random code to include in the email body
        int max = 999999;
        int min = 9999;
        SecureRandom secureRandom = new SecureRandom();
        int randomCode = secureRandom.nextInt(max - min) + min;

        // Build the HTML content of the email body
        String htmlMsg = "<h3>Password reset for your account</h3>"
                + "<p>Please click on the following link to reset your password:</p>"
                + "<p><a href='" + resetUrl + "'>" + resetUrl + "</a></p>"
                + "<p>Use this code to validate your reset request: " + randomCode + "</p>"
                + "<img src='http://www.apache.org/images/asf_logo_wide.gif'>";

        // Set the email message properties
        message.setContent(htmlMsg, "text/html");
        helper.setTo(receiverEmail);
        helper.setSubject("Password reset request for your account");
        this.emailSender.send(message);

        return randomCode;
    }


    //TO Be changed later
    @Override
    public User getConnectedUser(){
        return this.selectById(2L);
    }
}
