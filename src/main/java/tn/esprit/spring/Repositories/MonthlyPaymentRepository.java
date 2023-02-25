package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.MonthlyPayment;

import javax.persistence.criteria.CriteriaBuilder;

public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment, Integer> {
}
