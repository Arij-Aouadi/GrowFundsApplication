package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Investisment;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.InvesttismentRepository;
import tn.esprit.spring.Services.Interfaces.IInvesttismentServices;

import java.util.*;

@Service
@AllArgsConstructor

public class InvesttismentServices implements IInvesttismentServices {
    private InvesttismentRepository investtismentRepository;
    private UserService userService;

    @Override
    public Investisment add(Investisment i) {


        User u = userService.getConnectedUser();
        Date d= new Date();
        i.setInvestor(u);
        i.setDateInvest(d);
        return investtismentRepository.save(i);
    }

    @Override
    public Investisment edit(Investisment i) {
        return investtismentRepository.save(i);
    }

    @Override
    public List<Investisment> selectAll() {
        return  (List<Investisment>) investtismentRepository.findAll();
    }

    @Override
    public List<Investisment> selectByClient() {
        User u = userService.getConnectedUser();
        return investtismentRepository.findAllByInvestor(u);
    }

    @Override
    public Investisment selectById(int idinvesttisment) {

        return investtismentRepository.findById(idinvesttisment).get();
    }

    @Override
    public void deleteById(int idinvesttisment) {
        investtismentRepository.deleteById(idinvesttisment);

    }

    @Override
    public void delete(Investisment i) {
        investtismentRepository.delete(i);
    }
/*
    @Override
    public List<Investisment> addAll(List<Investisment> list) {
        return investtismentRepository.saveAll(list);
    }

    @Override

    public void deleteAll(List<Investisment> list) {
        investtismentRepository.deleteAll(list);
    }





    @Scheduled(cron = "0 0 0 31 12 *" )
    @Override
    public void finalAmount() {
        List<Investisment> listInves = (List<Investisment>) investtismentRepository.findAll();
        for (Investisment inv : listInves) {
            inv.setAmount((inv.getAmount() * (1 + inv.getTauxInvesttisment())));
            investtismentRepository.save(inv);
        }
    }
    @Override
    public void CalculateAmoutOfInves(int idInvestesment){
        Investisment inves =   investtismentRepository.findById(idInvestesment).orElse(null);
        inves.setAmount(inves.getAmount()+(inves.getAmount()*inves.getTauxInvesttisment()));
        investtismentRepository.save(inves);
    }


*/

}
















