package com.ms.stock.repository;

import com.ms.stock.entity.ProductInbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductInboxRepository extends JpaRepository<ProductInbox, Long> {

    Optional<ProductInbox> findByIdempotentKey(String idempotentKey);
 }
