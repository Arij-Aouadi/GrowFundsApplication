package tn.esprit.spring;

//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
//@EnableBatchProcessing
public class GrowFundsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowFundsApplication.class, args);
    }
}
