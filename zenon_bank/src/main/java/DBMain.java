import connection.ConnectionFactory;
import controller.TransactionIngestor;
import records.Transaction;
import repository.TransactionSQLRepository;

import java.util.List;

public class DBMain {
    static void main() {

        ConnectionFactory.getConnection();
        IO.println("Subiuuu");

        var repository = new TransactionSQLRepository();

        var transactionIngestor = new TransactionIngestor();

        long startTimeSQL = System.nanoTime();
        List<Transaction> transactions = transactionIngestor.readArchiveFiles("data/PS_20174392719_1491204439457_log.csv");
        IO.println(transactions.size());

        IO.println("Iniciando adicao das transacoes no BD...");
        transactions.forEach(repository::save);

        long endTimeSQL = System.nanoTime();
        IO.println("Tempo de inserção - SQL (ms): " + (endTimeSQL - startTimeSQL) / 1_000_000.0);

        repository.findCustomerOriginByName("C1231006815")
                .ifPresentOrElse(IO::println, () -> IO.println("Transacao nao encontrada para: C1231006815"));
    }
}
