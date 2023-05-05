package com.ms.stock.repository;

import com.ms.stock.entity.ProductOutbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductOutboxRepository extends JpaRepository<ProductOutbox,Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product_outbox  where id =:id", nativeQuery = true)
    void deleteProductOutbox(@Param("id") Long id);
}
