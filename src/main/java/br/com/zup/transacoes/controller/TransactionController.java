package br.com.zup.transacoes.controller;

import br.com.zup.transacoes.dto.request.TransactionMessageRequestDto;
import br.com.zup.transacoes.dto.response.TransactionDetailsDto;
import br.com.zup.transacoes.repository.CreditCardRepository;
import br.com.zup.transacoes.repository.TransactionRepository;
import br.com.zup.transacoes.service.RequestCreditCard;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private RequestCreditCard request;

    @Autowired
    private TransactionRepository transactionRepository;

    private final Tracer tracer;

    public TransactionController(Tracer tracer) {
        this.tracer = tracer;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping("/start")
    public ResponseEntity<?> requestTransactions(@RequestBody
                                                 @Valid TransactionMessageRequestDto transactionMessageRequestDto) {
        LOGGER.info("Solicitando recebimento de transações...");
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.transaction.action", "Start receiving messages");
        var response = request.request(transactionMessageRequestDto);

        return ResponseEntity.ok(response.body());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> stopTransactions(@PathVariable("id") String id) {

        LOGGER.info("Solicitando encerramento do recebimento de transações...");
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.transaction.action", "Stop receiving messages");

        return creditCardRepository.findById(id)
                .map(creditCard -> request.stopMessageStream(creditCard.getId()))
                .map(response -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends List<?>> findTop10ByCreditCard_IdOrderByCreatedAtDesc(@PathVariable("id") String id) {

        LOGGER.info("Listagem das compras mais recentes: ");
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.transaction.action", "List recent transactions");

        return creditCardRepository.findById(id)
                .map(creditCard -> ResponseEntity.ok(
                        transactionRepository.findTop10ByCreditCard_IdOrderByCreatedAtDesc(creditCard.getId())
                                .stream().map(TransactionDetailsDto::new).collect(Collectors.toList())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
