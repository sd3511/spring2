package ru.geekbrains.spring.winter.market.dtos;

import lombok.Data;
import ru.geekbrains.spring.winter.market.entities.Product;
import ru.geekbrains.spring.winter.market.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) { // TODO: Доработать в ДЗ

        if (items.stream().anyMatch(s -> s.getProductId().equals(product.getId()))) {
            int i = items.indexOf(items.stream()
                    .filter(s -> s.getProductId().equals(product.getId()))
                    .findFirst()
                    .orElseThrow(()->new ResourceNotFoundException("Данного продукта нет в корзине")));
            increaseQuantity(i, 1);
        } else {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
            recalculate();
        }

    }

    public void increaseQuantity(Integer id, Integer delta) {
        if (items.get(id).getQuantity() == 1 && delta < 0) {
            items.remove(((int) id));
        }else {
            items.get(id).setQuantity(items.get(id).getQuantity() + delta);
            items.get(id).setPrice(items.get(id).getPricePerProduct() * items.get(id).getQuantity());
        }
        items.removeIf(item -> item.getQuantity() == 0);

        recalculate();

    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void clear() {
        items.clear();
        recalculate();
    }
}
