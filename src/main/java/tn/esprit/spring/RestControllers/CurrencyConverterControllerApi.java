package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.math.BigDecimal;
@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/convert")
@AllArgsConstructor
public class CurrencyConverterControllerApi {


    @GetMapping("/{amount}/{fromCurrency}/to/{toCurrency}")
        public float convertCurrency(
                @PathVariable BigDecimal amount,
                @PathVariable String fromCurrency,
                @PathVariable String toCurrency) {
            System.out.println(amount);
            System.err.println(fromCurrency);
            System.out.println(toCurrency);
            MonetaryAmount monetaryAmount = Monetary.getDefaultAmountFactory()
                    .setCurrency(fromCurrency)
                    .setNumber(amount)
                    .create();

            CurrencyConversion currencyConversion = MonetaryConversions.getConversion(toCurrency);
            MonetaryAmount convertedAmount = monetaryAmount.with(currencyConversion);
            System.err.println(convertedAmount);
            if (convertedAmount != null) {
                return convertedAmount.getNumber().floatValue();
            } else {
                return 0;
            }
        }
    }
