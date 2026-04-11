import controller.TransactionIngestor;
import records.Transaction;

import java.math.BigDecimal;
import java.util.List;

import static records.Transaction.TypeTransaction.*;

public class Main {
    static void main() {



//        Transaction transaction1 = new Transaction(1L, PAYMENT, new BigDecimal("9839.64"), "C1231006815",new BigDecimal("170136.0"),new BigDecimal("160296.36"),
//            "M1979787155",new BigDecimal("0.0"),new BigDecimal("0.0"),0,0);
//
//        Transaction transaction2 = new Transaction(743L, CASH_OUT, new BigDecimal("850002.52"), "C1280323807",new BigDecimal("850002.52"),new BigDecimal("0.0"),
//                "C873221189",new BigDecimal("6510099.11"),new BigDecimal("7360101.63"),1,0);

        TransactionIngestor transactionIngestor = new TransactionIngestor();
        String fileName = "data/PS_20174392719_1491204439457_log.csv";
        List<Transaction> transactions = transactionIngestor.readArchiveFiles(fileName);
        transactions.stream().limit(10).forEach(System.out::println);
        System.out.println(transactions.size());

    }
}
