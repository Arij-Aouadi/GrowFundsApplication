package tn.esprit.spring.RestControllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import java.math.BigDecimal;

@RestController
@RequestMapping("/convert")
@AllArgsConstructor
public class CurrencyConverterControllerApi {


    @GetMapping("/{amount}/{fromCurrency}/to/{toCurrency}")
        public ResponseEntity<String> convertCurrency(
                @PathVariable BigDecimal amount,
                @PathVariable String fromCurrency,
                @PathVariable String toCurrency) {

            MonetaryAmount monetaryAmount = Monetary.getDefaultAmountFactory()
                    .setCurrency(fromCurrency)
                    .setNumber(amount)
                    .create();

            CurrencyConversion currencyConversion = MonetaryConversions.getConversion(toCurrency);

            MonetaryAmount convertedAmount = monetaryAmount.with(currencyConversion);

            if (convertedAmount != null) {
                return new ResponseEntity<>(
                        amount + " " + fromCurrency + " converted to " + toCurrency + ": " + convertedAmount.toString(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Conversion failed", HttpStatus.BAD_REQUEST);
            }
        }
    }
