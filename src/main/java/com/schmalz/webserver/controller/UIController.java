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
    public String submitForm(@ModelAttribute Expense expense){
        bl.addTransaction(expense);
        return "redirect:/";
    }

    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam(value ="id" ,required =true)Integer id){
        bl.deleteTransaction(id);
        return "redirect:/";
    }





    @GetMapping("/expenseForm")
    public String expenseForm(Model model){
        model.addAttribute("expense",new Expense());
        return "inputForm";
    }


    @GetMapping("/")
    public String index(@RequestParam(value="filter" ,required = false)Integer filter,
                        Model model,
                        @RequestParam(value = "param1",required = false)String param1,
                        @RequestParam(value = "param2",required = false)String param2){
        if(filter!=null){
            switch (filter){
                case 2:
                    if(param1!=null) {
                        model.addAttribute("filter", 2);
                        model.addAttribute("bl", bl.getForDaterange(param1, param2));
                    }
                    break;
                case 3:
                    if(param1!=null) {
                        model.addAttribute("filter", 3);
                        model.addAttribute("bl",bl.getForName(param1));
                        }
                    break;
                case 4:
                    if(param1!=null){
                        model.addAttribute("filter",4);
                        model.addAttribute("bl",bl.getForAmountRange(Integer.parseInt(param1),Integer.parseInt(param2)));
                    }

                    break;
                case 5:
                    if(param1!=null)
                    model.addAttribute("bl",bl.getWithKeyWord(param1));
                    break;
                    default:
                        model.addAttribute("bl",bl.getTransactions());
            }
        }
        model.addAttribute("time", LocalDateTime.now().toString());

        if(filter!=null)
            model.addAttribute("filter",filter);
        else{
            model.addAttribute("filter",1);
            model.addAttribute("bl",bl.getTransactions());
        }

        return "index";
    }


}
