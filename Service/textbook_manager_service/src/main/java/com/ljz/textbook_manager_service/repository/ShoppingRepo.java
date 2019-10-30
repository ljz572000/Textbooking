package com.ljz.textbook_manager_service.repository;

import com.ljz.textbook_manager_service.entity.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ShoppingRepo extends JpaRepository<ShoppingCart,Integer> {
    @Query(
            value = "select * from textbook_manager.shopping_cart ",
            countQuery = "select count(*) from textbook_manager.shopping_cart",
            nativeQuery = true
    )
    Page<ShoppingCart> findShoppingCarts(Pageable pageable);

    @Transactional
    @Modifying
    @Query(
            value = "insert into `shopping_cart`(`book_no`,`book_num`,`book_values`,`user_no`) values\n" +
                    " (?1,?2,?3,?4);",
            nativeQuery = true
    )
    void addShoppingCarts(Integer book_no,Integer book_num,Double book_values,Integer user_no);
}
