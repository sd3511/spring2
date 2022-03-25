package ru.geekbrains.spring.winter.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.winter.market.dtos.CategoryDto;
import ru.geekbrains.spring.winter.market.entities.Category;
import ru.geekbrains.spring.winter.market.services.ProductService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final ProductService productService;
    private final ProductConverter productConverter;


    public Category dtoToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setProducts(categoryDto.getProducts().stream().map(productConverter::dtoToEntity).collect(Collectors.toList()));
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        return category;
    }
    public CategoryDto entityToDto(Category category){
        CategoryDto c = new CategoryDto();
        c.setId(category.getId());
        c.setTitle(category.getTitle());
        c.setProducts(category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return c;
    }
}
