package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.PaymentMethod;
import tn.esprit.spring.DAO.Entities.TypePayment;

import java.util.List;

@AllArgsConstructor //injection par constructeur
@Service

public class PaymentMethodService implements IPaymentMethodService {
    private PaymentMethodRepository paymentMethodRepository ;
    @Override
    public PaymentMethod add(PaymentMethod p) {
        return paymentMethodRepository.save(p);
    }

    @Override
    public PaymentMethod edit(PaymentMethod p) {
        return paymentMethodRepository.save(p);

    }

    @Override
    public List<PaymentMethod> selectAll() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod selectById(int idPaymentMethod) {
        return paymentMethodRepository.findById(idPaymentMethod).get();
    }

    @Override
    public void deleteById(int idPaymentMethod) {
        paymentMethodRepository.deleteById(idPaymentMethod);


    }

    @Override
    public void delete(PaymentMethod p) {
        paymentMethodRepository.delete(p);

    }

    @Override
    public List<PaymentMethod> addAll(List<PaymentMethod> list) {
        return paymentMethodRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<PaymentMethod> list) {
        paymentMethodRepository.deleteAll(list);

    }

    @Override
    public List<PaymentMethod> selectByTypePayment(TypePayment type) {
        return paymentMethodRepository.findByTypePayment(type);
    }

}
