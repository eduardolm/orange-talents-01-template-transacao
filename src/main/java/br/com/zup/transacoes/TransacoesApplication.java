package br.com.zup.transacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
public class TransacoesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransacoesApplication.class, args);
    }

}
