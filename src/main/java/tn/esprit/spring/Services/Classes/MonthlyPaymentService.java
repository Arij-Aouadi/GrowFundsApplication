package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.MonthlyPayment;
import tn.esprit.spring.DAO.Repositories.CreditsRepository;
import tn.esprit.spring.DAO.Repositories.MonthlyPaymentRepository;
import tn.esprit.spring.Services.Interfaces.IMonthlyPayment;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor

public class MonthlyPaymentService implements IMonthlyPayment {
    private MonthlyPaymentRepository monthlyPaymentRepository;
    private CreditsRepository creditsRepository;
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

    @Override
    public MonthlyPayment CalculateDueDate(MonthlyPayment mp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(mp.getCredit().getDateDebut());
        cal.add(Calendar.DATE, mp.getIdPayment()*30);
        mp.setPaymentSupposedDate(cal.getTime());
        return mp;
    }

    @Override
    public int calculateLateDays(int idCredit) {

        Credits credit = creditsRepository.findCreditsByIdCredit(idCredit);

        int numberOfLateDays = 0;

        List<MonthlyPayment> ListMonthlyPayments = monthlyPaymentRepository.getCredit_DuesHistory(idCredit);

        for (MonthlyPayment payment : ListMonthlyPayments) {
            Date actualPaymentDate=payment.getPaymentDate();
            Date dueDate=payment.getPaymentSupposedDate();

            int monthlyDelay = (int)( ( dueDate.getTime()-actualPaymentDate.getTime())
                    / (1000 * 60 * 60 * 24) );
            numberOfLateDays=numberOfLateDays+monthlyDelay;
            credit.setCreditHistory(3);


        }
        return numberOfLateDays;
    }

}
