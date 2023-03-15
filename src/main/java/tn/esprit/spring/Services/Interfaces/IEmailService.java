package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Email;

public interface IEmailService {
    // Method
    // To send a simple email
    String sendSimpleEmail(Email details);
}
