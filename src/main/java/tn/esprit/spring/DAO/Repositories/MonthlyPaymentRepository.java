package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.MonthlyPayment;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.util.List;

public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment, Integer> {

    @Query("SELECT m FROM MonthlyPayment m WHERE m.credit.idCredit= :id_credit ")
    List<MonthlyPayment> getCredit_DuesHistory(@Param("id_credit") int idcredit);


}
