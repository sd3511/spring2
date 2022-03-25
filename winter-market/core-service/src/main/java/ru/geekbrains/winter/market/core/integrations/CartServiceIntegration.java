package ru.geekbrains.winter.market.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.winter.market.api.CartDto;
import ru.geekbrains.winter.market.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${cartService.url}")
    private String url;

    public Optional<CartDto> getCurrentCart() {
        return Optional.ofNullable(restTemplate.getForObject(url, CartDto.class));
    }

    public void clearCart(){
        restTemplate.getForObject(url+"clear", Void.class);
    }
}
