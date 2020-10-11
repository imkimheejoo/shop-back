package com.shop.demo.dto.response;

import com.shop.demo.dto.query.ProductInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductInfoResponseDto {

    private List<ProductInfoDto> contents;
    private Pageable pageable;

}
