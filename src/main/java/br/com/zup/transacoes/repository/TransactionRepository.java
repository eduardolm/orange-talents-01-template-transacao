package br.com.zup.transacoes.repository;

import br.com.zup.transacoes.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findTop10ByCreditCard_IdOrderByCreatedAtDesc(String creditCardId);
}
