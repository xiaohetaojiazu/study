package com.github.taohe.order.service;

import com.github.taohe.order.pojo.Order;
import com.github.taohe.order.mapper.OrderMapper;
import com.github.taohe.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {

        Order order = orderMapper.findById(orderId);

        completeOrder(order);

        return order;
    }

    private void completeOrder(Order order) {

//        String url = "http://localhost:8081/user/" + order.getUserId();
        String url = "http://userService/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
    }
}
