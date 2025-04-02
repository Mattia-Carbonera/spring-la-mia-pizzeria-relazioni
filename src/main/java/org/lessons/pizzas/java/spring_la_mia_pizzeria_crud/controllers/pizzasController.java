package org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;

import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Discount;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.repository.DiscountRepository;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.repository.pizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
public class pizzasController {

    private final DiscountRepository discountRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    public pizzaRepository repository;

    public pizzasController(IngredientRepository ingredientRepository, DiscountRepository discountRepository) {
        this.ingredientRepository = ingredientRepository;
        this.discountRepository = discountRepository;
    }

    // homePage
    @GetMapping("/")
    public String homePage() {
        return new String("index");
    }

    // about
    @GetMapping("/about")
    public String aboutPage() {
        return new String("about");
    }
    
    
    
    // index
    @GetMapping("/menu")
    public String index(Model model) {
        List<Pizza> pizzas = repository.findAll();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("modal", false);

        return new String("menuPage");
    }

    // show
    @GetMapping("/menu/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Pizza selectedPizza = repository.findById(id).get();
        model.addAttribute("pizza", selectedPizza);

        return new String("pizzaShow");
    }

    // search
    @GetMapping("/menu/search")
    public String searchPizza(@RequestParam("name") String name, Model model) {
        List<Pizza> searchedPizza = repository.findByNameContainingIgnoreCase(name);
        model.addAttribute("pizzas", searchedPizza);

        return new String("menuPage");
    }
    
    // store
    @GetMapping("/menu/create-pizza")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return new String("create");
    }

    @PostMapping("/menu/create-pizza")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new String("create");
        }
        model.addAttribute("ingredients", ingredientRepository.findAll());

        repository.save(formPizza);
        
        return new String("pizzaShow");
    }
    
    // edit
    @GetMapping("/menu/edit-pizza/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        
        return new String("edit");
    }
    
    @PostMapping("/menu/edit-pizza/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model ) {
        if(bindingResult.hasErrors()) {
            return new String("edit");
        }

        repository.save(formPizza);
        
        return new String("pizzaShow");
    }
    
    // delete
    @PostMapping("/menu/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Pizza pizzaToDelete = repository.findById(id).get();

        for (Discount discount : pizzaToDelete.getDiscount()) {
            discountRepository.delete(discount);
        }

        repository.delete(pizzaToDelete);
        
        return new String("redirect:/menu");
    }
    
    // * Discount
    // store Discount
    @GetMapping("/menu/{id}/create-discount")
    public String createDiscount(@PathVariable Integer id, Model model) {
        Discount discount = new Discount();
        discount.setPizza(repository.findById(id).get());
        model.addAttribute("discount", discount);
        model.addAttribute("edit", false);
        
        return new String("discount/discountCreateOrEdit");
    }
    
}
