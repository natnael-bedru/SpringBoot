package com.example.demo.repository;

import com.example.demo.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    //Optional<List<Order>> findProductNamesByUserId(Long userId);
    Optional<List<Order>> findProductNamesBy_userId(Long userId);

    Long findIdByProductName(String productName);

    @Query("SELECT o._user.id FROM Order o WHERE o.productName = :productName")
    Optional<List<Long>> findUserIdByProductName(String productName);

}

