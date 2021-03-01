package br.com.zup.transacoes.service;

import br.com.zup.transacoes.model.CreditCard;
import br.com.zup.transacoes.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    protected CreditCard checkCreditCardExists(String id) {
        Optional<CreditCard> creditCard = creditCardRepository.findById(id);
        if (creditCard.isEmpty()) {
            return null;
        }
        else {
            return creditCard.get();
        }
    }
}
