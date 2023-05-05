package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Account;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.AccountRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.Services.Interfaces.IUserService;

import java.util.List;


@AllArgsConstructor
@Service
@NoArgsConstructor

public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
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



    //TO Be changed later
    public User getConnectedUser(){
        return this.selectById(2L);
    }
}
