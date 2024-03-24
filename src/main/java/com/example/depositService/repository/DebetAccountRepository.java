package com.example.depositService.repository;

import com.example.depositService.entity.DebetAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebetAccountRepository extends JpaRepository<DebetAccount, Long> {
    DebetAccount findByUserId(Long userId);
}