package com.ms.order.repository;

import com.ms.order.entity.OrderInbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrderInboxRepository extends JpaRepository<OrderInbox,Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM order_inbox  where id =:id", nativeQuery = true)
    void deleteOrderInbox(@Param("id") Long id);

    Optional<OrderInbox> findByIdempotentKey(String idempotentKey);

 }
