package controller;

import records.Transaction;
import records.TransactionCustomer;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TransactionIngestor {

    public List<Transaction> readArchiveFiles(String fileName){
        Path path = Path.of(fileName);
        try{
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .skip(1)
                    .limit(100000)
                    .map(this::parseTransaction)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        }catch (Exception ex){
            throw new RuntimeException("Error to open archive: " + fileName, ex);
        }
    }

    public List<Optional<Transaction>>readArchiveFileInputStream (String fileName){

        List<Optional<Transaction>> transactionsList = new ArrayList<>();
        int countLines = 0;

        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            Scanner scanner = new Scanner(fileInputStream)){

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                countLines++;
                if (countLines == 1){
                    continue;
                } else if(countLines > 1001){
                    break;
                }
                transactionsList.add(parseTransaction(line));
            }
        } catch (Exception e){
            throw new RuntimeException("Error to open archive: " + fileName, e);
        }
        return transactionsList;
    }

    private Optional<Transaction> parseTransaction(String line) {
        try{

            String[] chunks = line.split(",");
            Long step = Long.parseLong(chunks[0]);
            Transaction.TypeTransaction  type = Transaction.TypeTransaction.valueOf(chunks[1]);

            if (chunks[2] == null || chunks[2].trim().isEmpty()) throw new IllegalAccessException("O valor de amount nao pode ser nulo nem vazio");
            BigDecimal amount = new BigDecimal(chunks[2]);

            String nameOrig = chunks[3];
            if (chunks[4] == null || chunks[4].trim().isEmpty()) throw new IllegalAccessException("O valor de amount nao pode ser nulo nem vazio");
            BigDecimal oldbalanceOrig = new BigDecimal(chunks[4]);

            if (chunks[5] == null || chunks[5].trim().isEmpty()) throw new IllegalAccessException("O valor de amount nao pode ser nulo nem vazio");
            BigDecimal newbalanceOrig = new BigDecimal(chunks[5]);

            String nameDest = (chunks[6]);

            if (chunks[7] == null || chunks[7].trim().isEmpty()) throw new IllegalAccessException("O valor de amount nao pode ser nulo nem vazio");
            BigDecimal oldbalanceDest = new BigDecimal(chunks[7]);

            if (chunks[8] == null || chunks[8].trim().isEmpty()) throw new IllegalAccessException("O valor de amount nao pode ser nulo nem vazio");
            BigDecimal newbalanceDest = new BigDecimal(chunks[8]);

            boolean isFraud = chunks[9].equals("1");
            boolean isFlaggedFraud = chunks[10].equals("1");

            TransactionCustomer customerOrigin = new TransactionCustomer(nameOrig, oldbalanceOrig, newbalanceOrig);
            TransactionCustomer customerDest = new TransactionCustomer(nameDest, oldbalanceDest, newbalanceDest);
            return Optional.of(new Transaction(step, type, amount, customerOrigin, customerDest, isFraud, isFlaggedFraud));
        } catch (Exception ex){
            System.err.println("Erro: " + line + "|" + ex);
        }

        return Optional.empty();
    }

}
