package repository;

import interfaces.TransactionRepository;
import records.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {

    private final List<Transaction> transactions;

    public TransactionListRepository(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }

    @Override
    public Optional<Transaction> findCustomerOriginByName(String nameOrig){
        return transactions.stream()
                .filter(transaction ->transaction.customersOrigin().name().equals(nameOrig))
                .findFirst();
    }

    @Override
    public void save(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
