package ru.geekbrainsspring.MarketSpring2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrainsspring.MarketSpring2.converters.ProductConverter;
import ru.geekbrainsspring.MarketSpring2.dto.ProductDto;
import ru.geekbrainsspring.MarketSpring2.entities.Category;
import ru.geekbrainsspring.MarketSpring2.entities.Order;
import ru.geekbrainsspring.MarketSpring2.entities.Product;
import ru.geekbrainsspring.MarketSpring2.exceptions.ResourceNotFoundException;
import ru.geekbrainsspring.MarketSpring2.repositories.CartRepository;
import ru.geekbrainsspring.MarketSpring2.services.CartService;
import ru.geekbrainsspring.MarketSpring2.services.CategoryService;
import ru.geekbrainsspring.MarketSpring2.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private  final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findAllProductsById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("продуктн не найден, id " + id));
        return productConverter.entityToDto(p);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
       Product p = productService.createNewProduct(productDto);
        return productConverter.entityToDto(p);
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
        order.setProductList(cartService.getProductList());
        cartRepository.save(order);
        cartService.clear();
        return "redirect:/order";
    }
}
