package ru.geekbrainsspring.MarketSpring2.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrainsspring.MarketSpring2.dto.ProductDto;
import ru.geekbrainsspring.MarketSpring2.entities.Order;
import ru.geekbrainsspring.MarketSpring2.entities.Product;
import ru.geekbrainsspring.MarketSpring2.exceptions.ResourceNotFoundException;
import ru.geekbrainsspring.MarketSpring2.repositories.CartRepository;
import ru.geekbrainsspring.MarketSpring2.services.CartService;
import ru.geekbrainsspring.MarketSpring2.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CartRepository cartRepository;
    private final CartService cartService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice())).collect(Collectors.toList());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findAllProductsById(@PathVariable Long id){
//        Optional<Product> product = productService.findById(id);
//        if (!product.isPresent()) {
//            ResponseEntity<AppError> err = new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "продуктн не найден, id " + id), HttpStatus.NOT_FOUND);
//        return err;
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ProductDto findAllProductsById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("продуктн не найден, id " + id));
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
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
