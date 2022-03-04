package com.example.servebyteserviceapplication.data.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MealItem {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private Integer quantityAddedCart;

        @ManyToOne(fetch = FetchType.EAGER)
        private Meal meal;

        public MealItem(Meal meal, Integer quantityAddedCart){
            if(quantityAddedCart <= meal.getQuantity()){
                this.quantityAddedCart = quantityAddedCart;
            }
            this.meal = meal;
        }

        public void setQuantityAddedCart(Integer quantityAddedCart){
            if(meal.getQuantity() >=quantityAddedCart){
                this.quantityAddedCart= quantityAddedCart;
            } else {
                this.quantityAddedCart=0;
            }
        }
}
