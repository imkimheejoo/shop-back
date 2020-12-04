package com.shop.demo.carts.domain;

import com.shop.demo.error.ErrorCode;
import com.shop.demo.error.exception.InvalidInputException;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
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
    @Min(1)
    private int count;

    public void increaseCount(int count) {
        checkValidCount(count);
        this.count+=count;
    }

    public void updateCount(int count) {
        checkValidCount(count);
        this.count = count;
    }

    private void checkValidCount(int count) {
        if (count <= 0) {
            throw new InvalidInputException(ErrorCode.INVALID_TYPE_VALUE);
        }
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
}
