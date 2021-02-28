package br.com.zup.transacoes.controller;

import br.com.zup.transacoes.dto.request.TransactionMessageRequestDto;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController extends CreditCardService {

    @Autowired
    private RequestCreditCard request;

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

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> stopTransactions(@PathVariable("id") UUID id) {
        LOGGER.info("Solicitando encerramento do recebimento de transações...");
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("tag.transaction.action", "Stop receiving messages");

        return checkCreditCardExists(id) == null ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(request.stopMessageStream(id));
    }
}