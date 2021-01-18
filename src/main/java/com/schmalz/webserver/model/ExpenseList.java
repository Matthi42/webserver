package com.schmalz.webserver.model;

import com.schmalz.webserver.Repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExpenseList {

    @Autowired
    private ExpenseRepository expenseRepository;

    public void addExpense(Expense expense){
        expenseRepository.save(expense);
    }
    public void deleteExpense(long id){
        expenseRepository.deleteById(id);
    }
    public void alterExpense(Expense expense){
        expenseRepository.existsById(expense.getId());
        expenseRepository.save(expense);
    }

    public List<Expense> getForDateRange(String start, String end){
        LocalDate date1=LocalDate.parse(start);
        LocalDate date2=LocalDate.parse(end);
        List<Expense> retur=new ArrayList<>();
        List<Expense>all = expenseRepository.findAll();
        for (Expense e:
             all) {
            if((e.getDate().isBefore(date2)||e.getDate().isEqual(date2))
                    &&((e.getDate().isAfter(date1))||(e.getDate().isEqual(date1)))){
                retur.add(e);
            }
        }
        return retur;
    }

    public List<Expense> getWithName(String name){
        List<Expense> retur=new ArrayList<>();
        List<Expense>all = expenseRepository.findAll();
        for (Expense e :
                all) {
            if (name.equalsIgnoreCase(e.getName())) {
                retur.add(e);
            }
        }
        return retur;
    }

    public List<Expense> getForAmountRange(int lowerLimit, int upperLimit) {
        List<Expense> retur=new ArrayList<>();
        List<Expense>all = expenseRepository.findAll();
        for (Expense e :
                all) {
            if (e.getBetrag() > lowerLimit && e.getBetrag() < upperLimit) {
                retur.add(e);
            }
        }
        return retur;
    }

    public List<Expense> getWithKeyword(String keyword){
        List<Expense> retur=new ArrayList<>();
        List<Expense>all = expenseRepository.findAll();
        for (Expense e :
                all) {
            if (e.getNotiz().toLowerCase().contains(keyword.toLowerCase()) &&
                    e.getName().toLowerCase().contains(keyword.toLowerCase()))
                retur.add(e);
        }
        return retur;
    }

    public Expense getByID(long id){
        return  expenseRepository.findById(id).get();
    }

    public List<Expense> getAll(){
        return expenseRepository.findAll();
    }

    public List<Expense> sortByDate(List<Expense> list,boolean incr){
        Comparator<Expense> comp=Comparator.comparing(Expense::getDate);
        if(!incr)
            list.sort(comp);
        else
            list.sort(Collections.reverseOrder(comp));
        return list;
    }

    public List<Expense> sortByName(List<Expense> list,boolean incr){
        Comparator<Expense> comp=Comparator.comparing(Expense::getName);
        if(!incr)
            list.sort(comp);
        else
            list.sort(Collections.reverseOrder(comp));
        return list;
    }

    public List<Expense> sortByAmount(List<Expense> list,boolean incr){
        Comparator<Expense> comp=Comparator.comparing(Expense::getBetrag);
        if(!incr)
            list.sort(comp);
        else
            list.sort(Collections.reverseOrder(comp));
        return list;
    }
}
