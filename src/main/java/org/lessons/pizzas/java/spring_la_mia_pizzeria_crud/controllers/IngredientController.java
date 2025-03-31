package org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    // Index
    @GetMapping("/")
    public String index(Model model) {
        return new String();
    }
    
    // create
    @GetMapping("/create")
    public String create(Model model) {
        Ingredient ingredient = new Ingredient();
        model.addAttribute("ingredient", ingredient);
        model.addAttribute("edit", false);
        return new String("ingredients/ingredientsCreateOrEdit");
    }
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "ingredients/ingredientsCreateOrEdit";
        }

        ingredientRepository.save(formIngredient);
        
        return new String("redirect:/menu");
    }
    
}
