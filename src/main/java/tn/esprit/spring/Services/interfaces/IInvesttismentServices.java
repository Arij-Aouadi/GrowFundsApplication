package tn.esprit.spring.Services.interfaces;

import tn.esprit.spring.DAO.Entities.Investtisment;

import java.util.List;

public interface IInvesttismentServices {
    Investtisment add(Investtisment i);
    Investtisment edit(Investtisment i);
    List<Investtisment> selectAll();
    Investtisment selectById(int idinvesttisment);
    void deleteById(int idinvesttisment);
    void delete(Investtisment a);
    List<Investtisment> addAll(List<Investtisment> list);
    void deleteAll(List<Investtisment> list);
}
