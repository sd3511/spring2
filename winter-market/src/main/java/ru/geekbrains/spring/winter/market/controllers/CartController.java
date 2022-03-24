package ru.geekbrains.spring.winter.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.winter.market.dtos.Cart;
import ru.geekbrains.spring.winter.market.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cartService.clearCart();
    }

    @GetMapping("/increase")
    public void increaseQuantity(@RequestParam Integer delta, @RequestParam Integer index){
        cartService.increaseQuantity(index, delta);
    }
    @GetMapping()
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }
}
