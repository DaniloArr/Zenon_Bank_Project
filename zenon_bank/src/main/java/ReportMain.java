import controller.TransactionIngestor;
import controller.TransactionReport;
import records.Transaction;

import java.util.List;

public class ReportMain {
    static void main() {

        TransactionReport transactionReport= new TransactionReport();
        String fileName = "data/PS_20174392719_1491204439457_log.csv";
        TransactionReport.Statistics statistics = transactionReport.generateReport(fileName);
        IO.println("""
            Total de linhas: %d
            Total de fraudes: %d
            Valor total transacionado: %.2f
            """.formatted(statistics.totalTransactions(), statistics.totalFrauds(), statistics.totalAmount()));
    }
}
