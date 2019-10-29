package com.ljz.textbook_manager_service.repository;


import com.ljz.textbook_manager_service.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Order,Integer> {
    @Query(
            value = "select * from textbook_manager.order ",
            countQuery = "select count(*) from textbook_manager.order",
            nativeQuery = true
    )
    Page<Order> findOrders(Pageable pageable);
}
