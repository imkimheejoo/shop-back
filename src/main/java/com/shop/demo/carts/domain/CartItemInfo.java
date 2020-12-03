package com.shop.demo.carts.domain;

import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.InvalidInputException;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CartItemInfo {

    @Column(nullable = false)
    private Long productId;

    private Long productOptionId;

    @Column(nullable = false)
    private int count;

    public void addCount(int count) {
        this.count+=count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemInfo that = (CartItemInfo) o;
        return Objects.equals(getProductId(), that.getProductId()) &&
                Objects.equals(getProductOptionId(), that.getProductOptionId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getProductOptionId());
    }

    public void updateCount(int count) {
        if (count <= 0) {
            throw new InvalidInputException(ErrorCode.INVALID_TYPE_VALUE);
        }
        this.count = count;
    }
}
