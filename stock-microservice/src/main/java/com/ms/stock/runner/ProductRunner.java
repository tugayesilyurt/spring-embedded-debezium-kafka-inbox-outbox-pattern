package com.ms.stock.runner;

import com.ms.stock.entity.Products;
import com.ms.stock.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductRunner implements CommandLineRunner {

    private final ProductsRepository productStockRepository;

    @Override
    public void run(String... args){

        productStockRepository.deleteAll();

        Products product = Products.builder()
                .productId(10l)
                .createdDate(LocalDateTime.now())
                .version(10)
                .stockSize(10)
                .totalAmount(new BigDecimal(100.20))
                .build();

        productStockRepository.save(product);

    }

}
