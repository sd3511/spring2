package ru.geekbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.winter.market.dtos.Cart;
import ru.geekbrains.spring.winter.market.entities.Product;
import ru.geekbrains.spring.winter.market.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final Cart cart;
    private final ProductService productService;
    @GetMapping
    public List<Product> getAllProducts(){
        return cart.getProductList();
    }

    @GetMapping("/add/{id}")
    public List<Product> addProductInCart(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        return cart.addToCart(product.get());


    }
    @GetMapping("/delete/{id}")
    public void deleteProductFromCart(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        cart.deleteProduct(product);


    }
}
