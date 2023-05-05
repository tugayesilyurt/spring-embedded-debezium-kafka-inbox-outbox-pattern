package com.ms.order.repository;

import com.ms.order.entity.OrderOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OrderOutboxRepository extends JpaRepository<OrderOutbox,Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM order_outbox  where id =:id", nativeQuery = true)
    void deleteOrderOutbox(@Param("id") Long id);

    Optional<OrderOutbox> findByOrderId(Long orderId);

 }
