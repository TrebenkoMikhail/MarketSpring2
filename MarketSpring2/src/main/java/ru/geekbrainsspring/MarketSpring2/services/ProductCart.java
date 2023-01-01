package ru.geekbrainsspring.MarketSpring2.services;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.geekbrainsspring.MarketSpring2.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@NoArgsConstructor
@SessionScope
public class ProductCart {
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product){
        productList.add(product);
    }

    public void clear(){
        productList.clear();
    }

}
