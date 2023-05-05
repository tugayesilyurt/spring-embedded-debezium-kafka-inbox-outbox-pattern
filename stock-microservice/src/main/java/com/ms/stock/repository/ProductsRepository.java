package com.ms.stock.repository;

import com.ms.stock.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Long> {

    Optional<Products> findByProductId(Long productId);


    @Modifying
    @Transactional
    @Query(value = "update products set version = version + 1,stock_size = stock_size - 1 " +
            "where id =:id and version =:version",nativeQuery = true)
    Integer updateProductStock(@Param("id") Long id,@Param("version") Integer version);


}
