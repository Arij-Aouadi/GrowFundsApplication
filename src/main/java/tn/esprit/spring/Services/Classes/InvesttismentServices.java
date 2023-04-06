package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.InvesttismentRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.Services.Interfaces.IInvesttismentServices;

import java.util.*;

@Service
@AllArgsConstructor

public class InvesttismentServices implements IInvesttismentServices {
    private InvesttismentRepository investtismentRepository;

    @Override
    public Investtisment add(Investtisment i) {
        return investtismentRepository.save(i);
    }

    @Override
    public Investtisment edit(Investtisment i) {
        return investtismentRepository.save(i);
    }

    @Override
    public List<Investtisment> selectAll() {
        return  (List<Investtisment>) investtismentRepository.findAll();
    }

    @Override
    public Investtisment selectById(int idinvesttisment) {
        return investtismentRepository.findById(idinvesttisment).get();
    }

    @Override
    public void deleteById(int idinvesttisment) {
        investtismentRepository.deleteById(idinvesttisment);

    }

    @Override
    public void delete(Investtisment i) {
        investtismentRepository.delete(i);
    }

    @Override
    public List<Investtisment> addAll(List<Investtisment> list) {
        return investtismentRepository.saveAll(list);
    }

    @Override

    public void deleteAll(List<Investtisment> list) {
        investtismentRepository.deleteAll(list);
    }





    @Scheduled(cron = "0 0 0 31 12 *" )
    @Override
    public void finalAmount() {
        List<Investtisment> listInves = (List<Investtisment>) investtismentRepository.findAll();
        for (Investtisment inv : listInves) {
            inv.setAmount((inv.getAmount() * (1 + inv.getTauxInvesttisment())));
            investtismentRepository.save(inv);
        }
    }
    @Override
    public void CalculateAmoutOfInves(int idInvestesment){
        Investtisment inves =   investtismentRepository.findById(idInvestesment).orElse(null);
        inves.setAmount(inves.getAmount()+(inves.getAmount()*inves.getTauxInvesttisment()));
        investtismentRepository.save(inves);
    }




}
















