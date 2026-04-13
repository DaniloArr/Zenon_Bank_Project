import controller.TransactionIngestor;
import interfaces.TransactionRepository;
import records.Transaction;
import repository.TransactionListRepository;
import repository.TransactionMapRepository;

import java.util.List;
import java.util.Optional;

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
//        transactions.forEach(System.out::println);
//        System.out.println(transactions.size());

        /*
        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(transactions);
        long totalFraudes = fraudAnalyzer.countFraud();
        System.out.println("Total de fraude eh: " + totalFraudes);

        List<Transaction> highestFrauds = fraudAnalyzer.findHighestValueFrauds(3);
        System.out.println("Maiores valores de fraude");
        highestFrauds.stream().map(Transaction::amount).forEach(System.out::println);

        List<String> highestSuspicous = fraudAnalyzer.findHighestSuspicousClients(5);
        System.out.println("Maiores suspeitos: ");
        highestSuspicous.forEach(System.out::println);

        BigDecimal totalFraude = fraudAnalyzer.prejuizoTotal();
        System.out.println("Prejuizo total: " + totalFraude);

        Map<Transaction.TypeTransaction, Long> countFrauds = fraudAnalyzer.countFraudsByType();
        System.out.println("Fraudes por tipo: ");
        System.out.println(countFrauds);

         */
        IO.println("--------------------------------------------------------------");

        TransactionRepository transactionRepository;

        transactionRepository = new TransactionListRepository(transactions);
        String notFoundOriginName = "C12345";
        transactionRepository.findCustomerOriginByName(notFoundOriginName)
                .ifPresentOrElse(IO::println, () -> IO.println("Transacao nao encontrada para " + notFoundOriginName));

        String existingOriginName = "C1868032458";

        long startTimeList = System.nanoTime();
        transactionRepository.findCustomerOriginByName(existingOriginName)
                .ifPresentOrElse(IO::println, () -> IO.println("Transacao nao encontrada para " + existingOriginName));
        long endTimeList = System.nanoTime();
        IO.println("Tempo de busca - List (ms): " + (endTimeList - startTimeList) / 1_000_000.0);

        transactionRepository = new TransactionMapRepository(transactions);
        startTimeList = System.nanoTime();
        transactionRepository.findCustomerOriginByName(existingOriginName)
                .ifPresentOrElse(IO::println, () -> IO.println("Transacao nao encontrada para " + existingOriginName));
        endTimeList = System.nanoTime();
        IO.println("Tempo de busca - Map (ms): " + (endTimeList - startTimeList) / 1_000_000.0);

    }
}
