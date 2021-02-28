package br.com.zup.transacoes.service;

import br.com.zup.transacoes.dto.request.TransactionMessageRequestDto;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
@Profile({"test", "dev", "prod"})
@FeignClient(name = "requestCreditCard", url = "http://localhost:7777")
public interface RequestCreditCard {

    @PostMapping("/api/cartoes")
    void request(@RequestBody TransactionMessageRequestDto requestDto);

    @DeleteMapping("/api/cartoes/{id}")
    Response stopMessageStream(@PathVariable("id")UUID id);
}
