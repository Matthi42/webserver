package com.schmalz.webserver.model;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;


public class BudgetList {
    private ArrayList<Transaction> Transactions = new ArrayList<>();

    private int IDcounter = 0;


    public void addTransaction(Transaction transaction) {
        this.Transactions.add(transaction);
    }

    public void addTransaction(Expense expense){
        addTransaction(new Transaction(expense,++IDcounter));
    }

    public void deleteTransaction(int id) {
        Transaction remove = null;
        for (Transaction t :
                Transactions) {
            if (t.getTransactionID() == id) {
                remove = t;
                break;
            }
        }
        try {
            Transactions.remove(remove);
        } catch (NullPointerException e) {
            System.out.println(":-(");
        }

    }





    public ArrayList<Transaction> getAtDate(LocalDateTime d) {
        ArrayList<Transaction> retur = new ArrayList<>();
        for (Transaction t :
                Transactions) {
            if (d.getDayOfYear() == t.getDate().getDayOfYear() && d.getYear() == t.getDate().getYear()) {
                retur.add(t);
            }
        }
        retur.sort(Comparator.comparing(Transaction::getName));
        return retur;
    }

    public ArrayList<Transaction> getForName(String name) {
        ArrayList<Transaction> retur = new ArrayList<>();
        for (Transaction t :
                Transactions) {
            if (name.equals(t.getName())) {
                retur.add(t);
            }
        }
        retur.sort(Comparator.comparing(Transaction::getDate));
        return retur;
    }

    public ArrayList<Transaction> getForAmountRange(int lowerLinit, int upperLimit) {
        ArrayList<Transaction> retur = new ArrayList<>();
        for (Transaction t :
                Transactions) {
            if (t.getBetrag() > lowerLinit && t.getBetrag() < upperLimit) {
                retur.add(t);
            }
        }
        retur.sort(Comparator.comparing(Transaction::getBetrag));
        return retur;
    }

    public ArrayList<Transaction> getWithKeyWord(String keyWord) {
        ArrayList<Transaction> retur = new ArrayList<>();
        for (Transaction t :
                Transactions) {
            if (t.getNotiz().contains(keyWord)) {
                retur.add(t);
            }
        }
        retur.sort(Comparator.comparing(Transaction::getBetrag));
        return retur;
    }

    public Transaction getByID(int id) {
        for (Transaction t :
                Transactions) {
            if (t.getTransactionID() == id)
                return t;
        }
        return null;
    }

    public ArrayList<Transaction> getTransactions() {
        return Transactions;
    }

    public int getSum() {
        return 0;
    }
}
