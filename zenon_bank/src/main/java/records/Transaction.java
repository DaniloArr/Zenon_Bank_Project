package records;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(int step, TypeTransaction type, BigDecimal amount, TransactionCustomer customersOrigin,
                          TransactionCustomer customersDest, boolean isFraud, boolean isFlaggedFraud) {

    public enum TypeTransaction{
        PAYMENT, DEBIT, TRANSFER, CASH_IN, CASH_OUT
    }

    public Transaction {
        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(customersOrigin);
        Objects.requireNonNull(customersDest);
        if (step <= 0) throw new IllegalArgumentException("O valor de step deve ser positivo: " + step);
        if (amount.signum() < 0) throw new IllegalArgumentException("O valor de amount deve ser positivo ou zero: " + amount);
    }



    @Override
    public String toString() {
        return "Transaction{" +
                "step=" + step +
                ", type=" + type +
                ", amount=" + amount +
                ", customersOrigin='" + customersOrigin + '\'' +
                ", customersOrigin='" + customersDest + '\'' +
                ", isFraud=" + isFraud +
                ", isFlaggedFraud=" + isFlaggedFraud +
                '}';
    }
}
