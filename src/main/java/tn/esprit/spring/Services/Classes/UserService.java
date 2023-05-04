package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.Services.Interfaces.IUserService;

import java.util.List;


@AllArgsConstructor
@Service
@NoArgsConstructor

public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
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
    public User getById(long id) {
        return userRepository.findById(id).get();
    }



    //TO Be changed later
    @Override
    public User getConnectedUser(){
        return this.selectById(2L);
    }
}
