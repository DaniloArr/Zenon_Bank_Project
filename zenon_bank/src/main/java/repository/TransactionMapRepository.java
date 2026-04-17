package repository;

import interfaces.TransactionRepository;
import records.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> transactionByOriginName;

    public TransactionMapRepository(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);

        this.transactionByOriginName =
                transactions
                        .stream()
                        .collect(Collectors.toMap(transaction -> transaction.customersOrigin().name(),
                                Function.identity()));
    }

    @Override
    public Optional<Transaction> findCustomerOriginByName(String nameOrig) {
        return Optional.ofNullable(transactionByOriginName.get(nameOrig));
    }

    @Override
    public void save(Transaction transaction) {
        this.transactionByOriginName.putIfAbsent(transaction.customersOrigin().name(), transaction);
    }


}
