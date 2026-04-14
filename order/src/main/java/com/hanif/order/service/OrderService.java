package com.hanif.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hanif.order.model.Order;
import com.hanif.order.repository.OrderRepository;
import com.hanif.order.vo.Produk;
import com.hanif.order.vo.ResponseTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order creatOrder(Order order) {
         if (order.getStatus() == null) {
            order.setStatus("NEW");
        }

        Order savedOrder = orderRepository.save(order);

        // 🔥 KIRIM KE RABBITMQ
        rabbitTemplate.convertAndSend("myQueue", savedOrder.toString());

        System.out.println("📤 Order dikirim ke RabbitMQ");

        return savedOrder;
    }

    @Transactional
    public void update(Long orderId, Integer jumlah, String tanggal, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Order tidak ada"));
        if (jumlah != null) {
            order.setJumlah(jumlah);
        }
        if (tanggal != null && tanggal.length() > 0
            && !Objects.equals(order.getTanggal(), tanggal)) {
                order.setTanggal(tanggal);
            }

        if (status != null && status.length() > 0) {
            order.setStatus(status);
        }
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<ResponseTemplate> getOrderWithProdukById(Long id) {
        List<ResponseTemplate> responseList = new ArrayList<>();
        Order order = getOrderById(id);
        ServiceInstance serviceInstance = discoveryClient.getInstances("PRODUK").get(0);
        String url = serviceInstance.getUri().toString() + "/api/produk/" + order.getId_produk();
        Produk produk = restTemplate.getForObject(url, Produk.class);
        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        responseList.add(vo);
        return responseList;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
