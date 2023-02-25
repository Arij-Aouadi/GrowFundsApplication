package tn.esprit.spring.RestControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.Services.interfaces.IInvesttismentServices;

import java.util.List;

@RestController
public class InvesttismentControllers {
    private IInvesttismentServices iInvesttismentServices;

    }



