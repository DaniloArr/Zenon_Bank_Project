package records;

import java.math.BigDecimal;

public record TransactionCustomer (String nameOrig, BigDecimal oldbalanceOrig, BigDecimal newbalanceOrig,
                                   String nameDest, BigDecimal oldbalanceDest, BigDecimal newbalanceDest){
    @Override
    public String toString() {
        return "TransactionCustomer{" +
                "nameOrig='" + nameOrig + '\'' +
                ", oldbalanceOrig=" + oldbalanceOrig +
                ", newbalanceOrig=" + newbalanceOrig +
                ", nameDest='" + nameDest + '\'' +
                ", oldbalanceDest=" + oldbalanceDest +
                ", newbalanceDest=" + newbalanceDest +
                '}';
    }
}
