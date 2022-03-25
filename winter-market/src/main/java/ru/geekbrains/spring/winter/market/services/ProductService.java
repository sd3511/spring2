package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.entities.Product;
import ru.geekbrains.spring.winter.market.repositories.ProductRepository;
import ru.geekbrains.spring.winter.market.soap.products.ProductSoap;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public static final Function<Product, ProductSoap> functionEntityToSoap = ge -> {
        ProductSoap p = new ProductSoap();
        p.setTitle(ge.getTitle());
        p.setCategoryTitle(ge.getCategory().getTitle());
        p.setPrice(ge.getPrice());
        p.setId(ge.getId());
        return p;
    };

    public Optional<ProductSoap> getById(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap);
    }

    public List<ProductSoap> getAllProducts(){
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }
}
