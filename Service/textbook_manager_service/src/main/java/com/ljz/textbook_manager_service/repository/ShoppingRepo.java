package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingRepo extends JpaRepository<ShoppingCart,Integer> {
    @Query(
            value = "select * from textbook_manager.shopping_cart ",
            countQuery = "select count(*) from textbook_manager.shopping_cart",
            nativeQuery = true
    )
    Page<ShoppingCart> findShoppingCarts(Pageable pageable);
}
