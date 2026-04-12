package records;

import java.math.BigDecimal;
import java.util.Objects;

public record TransactionCustomer (String name, BigDecimal oldBalance, BigDecimal newBalance){

    public TransactionCustomer{
        Objects.requireNonNull(name);
        if (name.trim().isEmpty()) throw new IllegalArgumentException("O nome do Customer nao pode ser nulo: " + oldBalance);
        Objects.requireNonNull(oldBalance);
        if (oldBalance.signum() < 0) throw new IllegalArgumentException("O valor de oldBalance deve ser positivo ou zero: " + oldBalance);
        Objects.requireNonNull(newBalance);
        if (newBalance.signum() < 0) throw new IllegalArgumentException("O valor de oldBalance deve ser positivo ou zero: " + newBalance);
    }

    @Override
    public String toString() {
        return "TransactionCustomer{" +
                "name='" + name + '\'' +
                ", oldBalance=" + oldBalance +
                ", newBalance=" + newBalance +
                '}';
    }
}
