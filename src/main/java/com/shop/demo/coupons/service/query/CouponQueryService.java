package com.shop.demo.coupons.service.query;

import com.shop.demo.coupons.domain.CouponType;
import com.shop.demo.coupons.repository.CouponRepository;
import com.shop.demo.dto.query.CouponInfoResponseDto;
import com.shop.demo.dto.query.CouponResponseDto;
import com.shop.demo.dto.query.OptionNameDto;
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

    public List<CouponInfoResponseDto> getAllCoupons() {
        return couponRepository.findAll().stream()
                .map(CouponInfoResponseDto::toDto)
                .collect(Collectors.toList());
    }
}
