package tn.esprit.spring.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.DAO.Entities.PaymentMethod;
import tn.esprit.spring.DAO.Entities.TypePayment;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod,Integer> {
    List<PaymentMethod> findByTypePayment(TypePayment type);
}
