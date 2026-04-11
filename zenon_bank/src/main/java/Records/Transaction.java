package Records;

import java.math.BigDecimal;

public record Transaction(Long step, TypeTransaction type, BigDecimal amount, String nameOrig, BigDecimal oldbalanceOrg, BigDecimal newbalanceOrig,
                          String nameDest, BigDecimal oldbalanceDest, BigDecimal newbalanceDest, int isFraud, int isFlaggedFraud) {

    public enum TypeTransaction{
        PAYMENT, DEBIT, TRANSFER, CASH_IN, CASH_OUT
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "step=" + step +
                ", type=" + type +
                ", amount=" + amount +
                ", nameOrig='" + nameOrig + '\'' +
                ", oldbalanceOrg=" + oldbalanceOrg +
                ", newbalanceOrig=" + newbalanceOrig +
                ", nameDest='" + nameDest + '\'' +
                ", oldbalanceDest=" + oldbalanceDest +
                ", newbalanceDest=" + newbalanceDest +
                ", isFraud=" + isFraud +
                ", isFlaggedFraud=" + isFlaggedFraud +
                '}';
    }
}
