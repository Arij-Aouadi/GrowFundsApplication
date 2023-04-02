package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.MonthlyPayment;

import java.util.List;

public interface IMonthlyPayment {
    MonthlyPayment add(MonthlyPayment mp);
    MonthlyPayment edit(MonthlyPayment mp);
    List<MonthlyPayment> selectAll();
    MonthlyPayment selectById(int numPayment);
    void delete(MonthlyPayment mp);
    void deleteById(int numPayment);
    List<MonthlyPayment> addAll (List<MonthlyPayment> List);
    void deleteAll(List<MonthlyPayment> list);

    MonthlyPayment CalculateDueDate(MonthlyPayment mp);

}
