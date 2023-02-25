package tn.esprit.spring.Services.Classes;

import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.Repositories.InvesttismentRepository;
import tn.esprit.spring.Services.interfaces.IInvesttismentServices;

import java.util.List;

public class InvesttismentServices implements IInvesttismentServices {
    private InvesttismentRepository investtismentRepository ;
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
    return investtismentRepository.findAll();
    }

    @Override
    public Investtisment selectById(int idinvesttisment) {
   return investtismentRepository.findById (idinvesttisment).get();
    }

    @Override
    public void deleteById(int idinvesttisment) {
        investtismentRepository.deleteById(idinvesttisment);

    }

    @Override
    public void delete(Investtisment a) {
  investtismentRepository.delete(a);
    }

    @Override
    public List<Investtisment> addAll(List<Investtisment> list) {
        return investtismentRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Investtisment> list) {
        investtismentRepository.deleteAll(list);
    }
}
