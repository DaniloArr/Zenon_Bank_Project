package interfaces;

import records.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    void save(Transaction transaction);
    Optional<Transaction> findCustomerOriginByName(String nameOrig);

}
