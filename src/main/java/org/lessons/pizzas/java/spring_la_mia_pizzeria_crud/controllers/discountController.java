package org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Discount;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RequestMapping("/discount")
public class discountController {

    @Autowired
    private DiscountRepository discountRepository;

    @GetMapping("/{id}")
    public String getMethodName(@PathVariable Integer id, Model model) {
        model.addAttribute("discount", discountRepository.findById(id).get());

        return new String("discount/discountShow");
    }
    


    // Create
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "discount/discountCreateOrEdit";
        }

        discountRepository.save(formDiscount);
        
        return new String("redirect:/menu");
    }

    // Edit
    @GetMapping("/{id}/edit-discount")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("discount", discountRepository.findById(id).get());
        model.addAttribute("edit", true);

        return new String("discount/discountCreateOrEdit");
    }

    @PostMapping("/{id}/edit-discount")
    public String update(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "discount/discountCreateOrEdit";
        }

        discountRepository.save(formDiscount);
        
        return new String("redirect:/menu");
    }

    // Delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        Discount discountToDelete = discountRepository.findById(id).get();
        discountRepository.delete(discountToDelete);
        
        return new String("redirect:/menu");
    }
    
    
    
    
    
}
