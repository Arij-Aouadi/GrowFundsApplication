package tn.esprit.spring.Services.Interfaces;


import tn.esprit.spring.DAO.Entities.Investisment;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;

public interface IInvesttismentServices {
    Investisment add(Investisment i);

    Investisment edit(Investisment i);
    List<Investisment> selectAll();
    List<Investisment> selectByClient();
    Investisment selectById(int idinvesttisment);
    void deleteById(int idinvesttisment);
    void delete(Investisment i);
   /* List<Investisment> addAll(List<Investisment> list);
    void deleteAll(List<Investisment> list);

    void finalAmount();
    void CalculateAmoutOfInves(int idInvestesment);
*/
}
