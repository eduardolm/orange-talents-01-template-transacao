package br.com.zup.transacoes.service;

import br.com.zup.transacoes.dto.response.TransactionMessageDto;
import br.com.zup.transacoes.model.CreditCard;
import br.com.zup.transacoes.model.Store;
import br.com.zup.transacoes.model.Transaction;
import br.com.zup.transacoes.repository.CreditCardRepository;
import br.com.zup.transacoes.repository.StoreRepository;
import br.com.zup.transacoes.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private StoreRepository storeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @KafkaListener(topics = "transacoes", groupId = "transaction_API", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void consumeMessage(TransactionMessageDto transactionMessageDto) {

        LOGGER.info("Processando transações recebidas...");
        LOGGER.info("Processando transação: " + transactionMessageDto);
        CreditCard creditCard = transactionMessageDto.getCartao();
        creditCardRepository.save(creditCard);
        Store store = transactionMessageDto.getEstabelecimento().toModel();
        storeRepository.save(store);
        Transaction transaction = transactionMessageDto.toModel();
        transaction.setStore(store);
        transaction.setCreditCard(creditCard);
        transactionRepository.save(transaction);
    }
}
