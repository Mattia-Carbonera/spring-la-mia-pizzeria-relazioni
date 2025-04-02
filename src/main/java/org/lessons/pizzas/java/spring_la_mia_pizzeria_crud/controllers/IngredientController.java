package org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.controllers;

import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    // Index
    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        ingredientRepository.findAll();

        return new String("ingredients/ingredientsShow");
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
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "ingredients/ingredientsCreateOrEdit";
        }

        ingredientRepository.save(formIngredient);

        model.addAttribute("ingredients", ingredientRepository.findAll());

        
        return new String("ingredients/ingredientsShow");
    }

    // edit
    @GetMapping("/{id}/edit-ingredient")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);

        return new String("ingredients/ingredientsCreateOrEdit");
    }

    @PostMapping("/{id}/edit-ingredient")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient updateIngredient, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "ingredients/ingredientsCreateOrEdit";
        }
        
        ingredientRepository.save(updateIngredient);

        model.addAttribute("ingredients", ingredientRepository.findAll());

        
        return new String("ingredients/ingredientsShow");
    }
    
    // Delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {
        Ingredient ingredientToDelete = ingredientRepository.findById(id).get();

        for(Pizza pizzaWithIngredient : ingredientToDelete.getPizzas()) {
            pizzaWithIngredient.getIngredients().remove(ingredientToDelete);
        }

        ingredientRepository.delete(ingredientToDelete);
        model.addAttribute("ingredients", ingredientRepository.findAll());
        
        return new String("ingredients/ingredientsShow");
    }
    
    
    
}
