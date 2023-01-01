package ru.geekbrainsspring.MarketSpring2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrainsspring.MarketSpring2.entities.Order;
import ru.geekbrainsspring.MarketSpring2.entities.Product;
import ru.geekbrainsspring.MarketSpring2.repositories.CartRepository;
import ru.geekbrainsspring.MarketSpring2.services.ProductCart;
import ru.geekbrainsspring.MarketSpring2.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CartRepository cartRepository;
    private final ProductCart productCart;

    @GetMapping
    List<Product> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findAllProductsById(@PathVariable Long id){
        return productService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
    @GetMapping("/order")
    public String get(Model model) {
        List<Order> orders = cartRepository.findAll();
        model.addAttribute("orders", orders);
        return "order";
    }

    @PostMapping("/order")
    public String order(@RequestParam String phone) {
        Order order = new Order();
        order.setPhone(phone);
        order.setProductList(productCart.getProductList());
        cartRepository.save(order);
        productCart.clear();
        return "redirect:/order";
    }
}
