package br.com.marciocostarjr;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableScheduling
public class PrincipalProdutor {

    public static void main(String[] args) {
        run(PrincipalProdutor.class, args);
    }
}
