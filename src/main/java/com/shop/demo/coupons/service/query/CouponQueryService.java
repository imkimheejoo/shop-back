package com.shop.demo.coupons.service.query;

import com.shop.demo.coupons.repository.CouponRepository;
import com.shop.demo.dto.query.CouponResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CouponQueryService {

    private final CouponRepository couponRepository;

    public List<CouponResponseDto> getCoupons(Long accountId) {
        return couponRepository.findUnUsedCouponByAccountId(accountId).stream()
                .map(CouponResponseDto::toDto)
                .collect(Collectors.toList());
    }
}
