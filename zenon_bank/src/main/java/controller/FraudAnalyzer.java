package controller;

import records.Transaction;
import records.TransactionCustomer;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }

    public long countFraud (){
        return transactions.stream()
                    .filter(Transaction::isFraud)
                    .count();
    }

    public List<Transaction> findHighestValueFrauds (int limit){
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .limit(limit)
                .toList();
    }

    public List<String> findHighestSuspicousClients (int limit){
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(transaction -> transaction.customersOrigin().name())
                .distinct()
                .limit(limit)
                .toList();
    }

    public BigDecimal prejuizoTotal (){
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public Map<Transaction.TypeTransaction, Long> countFraudsByType(){
        return transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }
}
