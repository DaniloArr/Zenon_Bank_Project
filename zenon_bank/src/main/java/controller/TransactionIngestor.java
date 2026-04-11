package controller;

import records.Transaction;
import records.TransactionCustomer;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionIngestor {

    public List<Transaction> readArchiveFiles(String fileName){
        Path path = Path.of(fileName);

        try{
            List<String> lines = Files.readAllLines(path);
            return lines.stream()
                    .skip(1)
                    .limit(1001)
                    .map(this::parseTransaction)
                    .toList();
        }catch (Exception ex){
            throw new RuntimeException("Error to open archive: " + fileName, ex);
        }
    }

    public List<Transaction> readArchiveFileInputStream (String fileName){

        List<Transaction> transactionsList = new ArrayList<>();
        int countLines = 0;
        //Path path = Paths.get("data/PS_20174392719_1491204439457_log.csv");

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

    private Transaction parseTransaction(String line) {

        String[] chunks = line.split(",");
        Long step = Long.parseLong(chunks[0]);
        Transaction.TypeTransaction  type = Transaction.TypeTransaction.valueOf(chunks[1]);
        BigDecimal amount = new BigDecimal(chunks[2]);
        String nameOrig = chunks[3];
        BigDecimal oldbalanceOrig = new BigDecimal(chunks[4]);
        BigDecimal newbalanceOrig = new BigDecimal(chunks[5]);
        String nameDest = (chunks[6]);
        BigDecimal oldbalanceDest = new BigDecimal(chunks[7]);
        BigDecimal newbalanceDest = new BigDecimal(chunks[8]);
        boolean isFraud = chunks[9].equals("1");
        boolean isFlaggedFraud = chunks[10].equals("1");

        TransactionCustomer transactionCustomer = new TransactionCustomer(nameOrig, oldbalanceOrig, newbalanceOrig, nameDest, oldbalanceDest, newbalanceDest);
        return new Transaction(step,type, amount, transactionCustomer, isFraud, isFlaggedFraud);
    }

}
