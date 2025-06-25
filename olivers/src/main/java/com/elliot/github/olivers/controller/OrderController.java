package com.elliot.github.olivers.controller;

import com.elliot.github.olivers.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/queryOrder")
    public void queryOrder(){
        System.out.println("queryOrder");
    }

    @PostMapping("/creatOrder")
    public void creatOrder(){
        orderService.creatOrder();
    }

    @PutMapping("/payOrder")
    public void payOrder(){
        orderService.payOrder();
    }
}
