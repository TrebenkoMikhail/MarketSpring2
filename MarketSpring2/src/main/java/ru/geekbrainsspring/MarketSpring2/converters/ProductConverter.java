package ru.geekbrainsspring.MarketSpring2.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrainsspring.MarketSpring2.dto.ProductDto;
import ru.geekbrainsspring.MarketSpring2.entities.Category;
import ru.geekbrainsspring.MarketSpring2.entities.Product;
import ru.geekbrainsspring.MarketSpring2.exceptions.ResourceNotFoundException;
import ru.geekbrainsspring.MarketSpring2.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }
    public Product dtoToEntity (ProductDto productDto) {
        Product p = new Product();
        p.setId(productDto.getId());
        p.setTitle(productDto.getTitle());
        p.setPrice(productDto.getPrice());
        Category c = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
        p.setCategory(c);
        return p;
    }
}
