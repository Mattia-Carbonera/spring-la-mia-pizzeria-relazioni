package org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.repository;

import org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    
}
