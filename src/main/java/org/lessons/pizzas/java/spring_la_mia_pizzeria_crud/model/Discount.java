package org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
// ! TODO: Change name of table

@Entity
@Table(name = "discounts")
public class Discount {

    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotEmpty( message = "The Title can't be empty or null")
    public String title;

    // @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "The offer start date cannot be null")
    @PastOrPresent (message = "The offer start date cannot be a future date")
    public Date startingDate;

    // @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "The offer start date cannot be null")
    @Future (message = "The offer end date cannot be a date in the past")
    public Date finishDate;

    @ManyToOne
    @JoinColumn( name = "pizza_id", nullable = false)
    private Pizza pizza;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartingDate() {
        return this.startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getFinishDate() {
        return this.finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    
}
