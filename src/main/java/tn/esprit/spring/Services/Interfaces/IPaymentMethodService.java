package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.PaymentMethod;
import tn.esprit.spring.DAO.Entities.TypePayment;

import java.util.List;

public interface IPaymentMethodService {

    PaymentMethod add(PaymentMethod p);
    PaymentMethod edit(PaymentMethod p);
    List<PaymentMethod> selectAll();
    PaymentMethod selectById(int idPaymentMethod );
    void deleteById(int idPaymentMethod );
    void delete(PaymentMethod p);
    List<PaymentMethod> addAll(List<PaymentMethod> list);
    void deleteAll(List<PaymentMethod> list);

    List<PaymentMethod> selectByTypePayment(TypePayment type);
}

