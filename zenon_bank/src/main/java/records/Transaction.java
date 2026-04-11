package records;

import java.math.BigDecimal;

public record Transaction(Long step, TypeTransaction type, BigDecimal amount, TransactionCustomer dataCustomers,
                           boolean isFraud, boolean isFlaggedFraud) {

    public enum TypeTransaction{
        PAYMENT, DEBIT, TRANSFER, CASH_IN, CASH_OUT
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "step=" + step +
                ", type=" + type +
                ", amount=" + amount +
                ", dataCustomers='" + dataCustomers + '\'' +
                ", isFraud=" + isFraud +
                ", isFlaggedFraud=" + isFlaggedFraud +
                '}';
    }
}
