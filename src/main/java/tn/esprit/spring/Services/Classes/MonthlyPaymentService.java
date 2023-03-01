package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.MonthlyPayment;
import tn.esprit.spring.DAO.Repositories.MonthlyPaymentRepository;
import tn.esprit.spring.Services.Interfaces.IMonthlyPayment;

import java.util.List;

@Service
@AllArgsConstructor

public class MonthlyPaymentService implements IMonthlyPayment {
    private MonthlyPaymentRepository monthlyPaymentRepository;
    @Override
    public MonthlyPayment add(MonthlyPayment mp) {
        return monthlyPaymentRepository.save(mp);
    }

    @Override
    public MonthlyPayment edit(MonthlyPayment mp) {
        return monthlyPaymentRepository.save(mp);
    }

    @Override
    public List<MonthlyPayment> selectAll() {
        return monthlyPaymentRepository.findAll();
    }

    @Override
    public MonthlyPayment selectById(int numPayment) {
        return monthlyPaymentRepository.findById(numPayment).get() ;
    }

    @Override
    public void delete(MonthlyPayment mp) {
        monthlyPaymentRepository.delete(mp);
    }

    @Override
    public void deleteById(int numPayment) {
        monthlyPaymentRepository.deleteById(numPayment);
    }

    @Override
    public List<MonthlyPayment> addAll(List<MonthlyPayment> list) {
        return monthlyPaymentRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<MonthlyPayment> list) {
        monthlyPaymentRepository.deleteAll(list);
    }
}
