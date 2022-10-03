package br.com.marciocostarjr;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableScheduling
public class PrincipalConsumidor {

    public static void main(String[] args) {
        run(PrincipalConsumidor.class, args);
    }

}
