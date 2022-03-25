package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.winter.market.dtos.OrderInfo;
import ru.geekbrains.spring.winter.market.entities.Order;
import ru.geekbrains.spring.winter.market.entities.User;
import ru.geekbrains.spring.winter.market.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    @Transactional
    public void createOrder(User user, OrderInfo orderInfo) {
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(orderInfo.getTotalPrice());
        order.setPhone(orderInfo.getPhone());
        order.setAddress(orderInfo.getAdress());

        Order order1 = orderRepository.save(order);
        orderItemService.createOrderItem(order1, orderInfo);
    }

}
