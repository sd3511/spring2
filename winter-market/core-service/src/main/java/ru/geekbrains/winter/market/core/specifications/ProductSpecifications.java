package ru.geekbrains.winter.market.core.specifications;


import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.winter.market.core.entities.Product;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(Integer price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLesserOrEqualsThan(Integer price) {

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);

    }

    public static Specification<Product> likeTitle(String titlePart) {

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%",titlePart));

    }


}
