package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.dtos.OrderInfo;
import ru.geekbrains.spring.winter.market.dtos.ProductDto;
import ru.geekbrains.spring.winter.market.entities.Order;
import ru.geekbrains.spring.winter.market.entities.OrderItem;
import ru.geekbrains.spring.winter.market.entities.Product;
import ru.geekbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.winter.market.model.Cart;
import ru.geekbrains.spring.winter.market.repositories.OrderItemRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    public void createOrderItem(Order order1, OrderInfo orderInfo){
        List<OrderItem> orderItemList = orderInfo.getItems().stream()
                .map(item -> new OrderItem(null, getProduct(item.getProductId()), order1, item.getQuantity(), item.getPricePerProduct(), item.getPrice(), null, null))
                .collect(Collectors.toList());
        orderItemRepository.saveAll(orderItemList);
    }
    private Product getProduct(Long id){
        return productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found"));
    }
}
