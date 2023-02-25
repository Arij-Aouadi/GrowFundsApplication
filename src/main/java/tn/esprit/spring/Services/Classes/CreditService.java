package tn.esprit.spring.Services.Classes;

import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Repositories.CreditsRepository;
import tn.esprit.spring.Services.Interfaces.ICreditService;

import java.util.List;

@RestController
public class CreditService implements ICreditService {
    private CreditsRepository creditsRepository;
    @Override
    public Credits add(Credits c) {
        return creditsRepository.save(c) ;
    }

    @Override
    public Credits edit(Credits c) {
        return creditsRepository.save(c);
    }

    @Override
    public List<Credits> selectAll() {
        return creditsRepository.findAll();
    }

    @Override
    public Credits selectById(int idCredit) {
        return creditsRepository.findById(idCredit).get();
    }

    @Override
    public void delete(Credits c) {
        creditsRepository.delete(c);
    }

    @Override
    public void deleteById(int idCredit) {
        creditsRepository.deleteById(idCredit);
    }

    @Override
    public List<Credits> addAll(List<Credits> List) {
        return creditsRepository.saveAll(List);
    }

    @Override
    public void deleteAll(List<Credits> list) {
        creditsRepository.deleteAll(list);
    }
}
