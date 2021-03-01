package br.com.zup.transacoes.controller;

import br.com.zup.transacoes.dto.request.TransactionMessageRequestDto;
import br.com.zup.transacoes.dto.response.TransactionDetailsDto;
import br.com.zup.transacoes.repository.TransactionRepository;
import br.com.zup.transacoes.service.CreditCardService;
import br.com.zup.transacoes.service.RequestCreditCard;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController extends CreditCardService {

    @Autowired
    private RequestCreditCard request;

    @Autowired
    private TransactionRepository repository;

    private final Tracer tracer;

    public TransactionController(Tracer tracer) {
        this.tracer = tracer;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @PostMapping
    public ResponseEntity<?> requestTransactions(@RequestBody
                                                 @Valid TransactionMessageRequestDto transactionMessageRequestDto) {
        LOGGER.info("Solicitando recebimento de transações...");
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.transaction.action", "Start receiving messages");
        var response = request.request(transactionMessageRequestDto);

        return ResponseEntity.ok(response.body());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> stopTransactions(@PathVariable("id") String id) {
        LOGGER.info("Solicitando encerramento do recebimento de transações...");
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.transaction.action", "Stop receiving messages");

        var response = checkCreditCardExists(id) == null ? null : request.stopMessageStream(id);

        return response == null ? ResponseEntity.notFound().build() :
                ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<?>> findTop10ByCreditCard_IdOrderByCreatedAtDesc(
            @PathParam(value = "creditCard_Id") String creditCardId) {

        if (creditCardId == null) {
            throw new IllegalArgumentException("É obrigatório informar o número do cartão.");
        }

        return checkCreditCardExists(creditCardId) == null ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(repository.findTop10ByCreditCard_IdOrderByCreatedAtDesc(creditCardId)
                        .stream()
                        .map(TransactionDetailsDto::new)
                        .collect(Collectors.toList()));
    }
}
