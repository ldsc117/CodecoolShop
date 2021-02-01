package com.codecool.shop.controller;


import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class CartController {
    private static HashMap<Product, Integer> listOfCart;
    private float totalPrice;


    public static void setListOfCart(HashMap<Product, Integer> listOfCart) {
        CartController.listOfCart = listOfCart;
    }

    public static HashMap<Product, Integer> getListOfCart() {
        return listOfCart;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getTotalPrice() {
        for (Map.Entry<Product, Integer> entry : listOfCart.entrySet()) {
            totalPrice += entry.getValue() * entry.getKey().getDefaultPrice();
        }
        return totalPrice;
    }

}
