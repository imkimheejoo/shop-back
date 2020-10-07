package com.shop.demo.accounts.repository;

import com.shop.demo.accounts.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
