package com.schmalz.webserver.controller;

import com.schmalz.webserver.Filter;
import com.schmalz.webserver.model.BudgetList;
import com.schmalz.webserver.model.Expense;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class UIController {
    private BudgetList bl=new BudgetList();


    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute Expense expense,Model model){
        bl.addTransaction(expense);
        return "index";
    }

    @PostMapping("/changeFilter")
    public String changeFilter(@ModelAttribute Filter filter, Model model){

        return "index";
    }



    @GetMapping("/expenseForm")
    public String expenseForm(Model model){
        model.addAttribute("expense",new Expense());
        return "inputForm";
    }


    @GetMapping("/")
    public String index(@RequestParam(value="filter" ,required = false)Integer filter,Model model){

        model.addAttribute("time", LocalDateTime.now().toString());
        //hier muss nach dem übergebenen parameter gefiltert werden
        model.addAttribute("bl",bl.getTransactions());
        model.addAttribute(filter);
        return "index";
    }


}
