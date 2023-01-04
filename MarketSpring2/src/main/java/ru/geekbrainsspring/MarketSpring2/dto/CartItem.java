package ru.geekbrainsspring.MarketSpring2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartItem {
    private Long productId;
    private String productTitle;
    private  int quantity;
    private int pricePerProduct;
    private int price;


}
