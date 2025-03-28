package org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Discount;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/discount")
public class discountController {

    @Autowired
    private DiscountRepository discountRepository;


    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "discount/discountCreate";
        }

        discountRepository.save(formDiscount);
        
        return new String("redirect:/menu");
    }
    
    
}
