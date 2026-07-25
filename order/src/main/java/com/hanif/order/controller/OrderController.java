package com.hanif.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanif.order.model.Order;
import com.hanif.order.service.OrderService;
import com.hanif.order.vo.ResponseTemplate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        log.info("START - getAllOrders");
        var orders = orderService.getAllOrders();
        log.info("END - getAllOrders");
        return orders;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") Long id) {
        log.info("START - getOrderById");
        var order = orderService.getOrderById(id);
        log.info("END - getOrderById");
        return order;
    }
    
    @GetMapping("/produk/{id}")
    public List<ResponseTemplate> getOrderWithProdukId(@PathVariable("id") Long id) {
        log.info("START - getOrderWithProdukId");
        var orders = orderService.getOrderWithProdukById(id);
        log.info("END - getOrderWithProdukId");
        return orders;
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable("id") Long id,
            @RequestParam(required = false) int jumlah,
            @RequestParam(required = false) String tanggal,
            @RequestParam(required = false) String status) {
        log.info("START - updateOrder");
        orderService.update(id, jumlah, tanggal, status);
        log.info("END - updateOrder");
    }
    
    
    @PostMapping
    public Order createOrder(@RequestBody Order order, Authentication auth) {
        log.info("START - createOrder");
        if (auth == null) {
            log.info("END - createOrder");
            return null;
        }

        String username = auth.getName(); // 🔥 ambil dari JWT
        String role = auth.getAuthorities()
        .iterator()
        .next()
        .getAuthority();

        // opsional: set ke order
        order.setCreatedBy(username);
        order.setRole(role);

        var createdOrder = orderService.creatOrder(order);
        log.info("END - createOrder");
        return createdOrder;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        log.info("START - deleteOrder");
        orderService.deleteOrder(id);
        log.info("END - deleteOrder");
        return ResponseEntity.ok().build();
    }
    
}
