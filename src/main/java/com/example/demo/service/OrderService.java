package com.example.demo.service;

import com.example.demo.repository.OrderRepository;
import com.example.demo.domain.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }
    public Optional<List<Order>>  findProductNamesBy_userId(Long user_id) {
        return orderRepository.findProductNamesBy_userId(user_id);
    }
    //Optional<List<Long>> findUserIdsByProductName(String productName);
    public Optional<List<Long>> findUserIdsByProductName (String productName) {
        return orderRepository.findUserIdByProductName(productName);
    }

    public Optional<Long> findIdByProductName(String productName){
        return Optional.ofNullable(orderRepository.findIdByProductName(productName));
    }

    public Order updateOrder(Order updatedOrder) {
        Order existingOrder = orderRepository.findById(updatedOrder.getId())
                .orElseThrow(() -> new NoSuchElementException("Order not found"));

        existingOrder.setOrderDate(LocalDate.now());
        // Update other fields as needed

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

