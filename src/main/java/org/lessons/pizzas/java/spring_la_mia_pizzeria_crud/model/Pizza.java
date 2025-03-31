package org.lessons.pizzas.java.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    // @Column( name = "nome_nel_database")
    @NotEmpty(message = "The name of pizza can't be empty")
    String name;

    @Lob
    @NotEmpty(message = "The description of pizza can't be empty")
    String description;

    String image;
    
    @Min(value = 0, message = "The price of pizza can't be negative")
    @NotNull(message = "The price of pizza can't be null")
    BigDecimal price;

    // Relations
    @OneToMany(mappedBy = "pizza")
    private List<Discount> discount;

    @ManyToMany
    @JoinTable(
        name = "ingredient_pizza",
        joinColumns = @JoinColumn(name = "pizza_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;



    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        if(image.length() > 0) {
            this.image = image;
        } else {
            this.image = null;
        }
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }


    public List<Discount> getDiscount() {
        return this.discount;
    }

    public void setDiscount(List<Discount> discount) {
        this.discount = discount;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }



}
