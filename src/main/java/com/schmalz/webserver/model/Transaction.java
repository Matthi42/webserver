package com.schmalz.webserver.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.spi.LocaleServiceProvider;

@Component
public class Transaction {



    private int transactionID;
    private Expense expense;
    //private LocalDateTime submitTime;


    public Transaction(int betrag, String name, LocalDate date, String notiz, int transactionID){
        this.expense=new Expense(betrag,name,date,notiz);
        this.transactionID=transactionID;
    }
    public Transaction(Expense expense, int transactionID){
        this.expense=expense;
        this.transactionID=transactionID;
        //this.submitTime=LocalDateTime.now();
    }



    public Transaction(){

    }

    public int getBetrag() {
        return expense.getBetrag();
    }

    public String getBetragInEuro(){
        return expense.getBetragInEuro();

    }
    public String getName(){
        return expense.getName();
    }
    public String getNotiz(){
        return expense.getNotiz();
    }
    public LocalDate getDate(){
        return expense.getDate();
    }
   //public LocalDateTime getSubmitTime(){
   //    return submitTime;
   //}
    public Expense getExpense() {return expense;}

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setName(String name){
        this.expense.setName(name);
    }

    public void setNotiz(String notiz){
        this.expense.setNotiz(notiz);
    }

    public void setDate(LocalDate date){
        this.expense.setDate(date);
    }

    public void setBetrag(int betrag){
        this.expense.setBetrag(betrag);
    }


    @Override
    public String toString(){
        return transactionID+expense.toString();
    }

}