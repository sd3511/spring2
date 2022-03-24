package ru.geekbrains.spring.winter.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.winter.market.model.CartItem;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    private List<CartItem> items;
    //Тут думал просто корзину целиком с фронта передавать.
    // Но не понял как зашить и корзину и OrderInfo в один JSON и можно ли так сделать вообще
    private int totalPrice;
    private String adress;
    private String phone;
}
