package Records;

import java.math.BigDecimal;

public record Transaction(Long step, TypeTransaction type, BigDecimal amount, String nameOrig, BigDecimal oldbalanceOrg, BigDecimal newbalanceOrig,
                          String nameDest, BigDecimal oldbalanceDest, BigDecimal newbalanceDest, int isFraud, int isFlaggedFraud) {

    public enum TypeTransaction{
        PAYMENT, DEBIT, TRANSFER, CASH_IN, CASH_OUT
    }
}
