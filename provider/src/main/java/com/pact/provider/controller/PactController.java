package com.pact.provider.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pact")
@SuppressWarnings("unused")
public class PactController {

    @GetMapping
    public ResponseEntity getAllOrders() {
        Map orderId = getOrderList();
        if(orderId == null) {
            throw new NullPointerException("Order List Empty");
        }

        return new ResponseEntity(orderId, HttpStatus.OK);
    }

    @GetMapping("/create")
    public ResponseEntity createOrders() {
        return new ResponseEntity(HttpStatus.CREATED);
    }


    private String getOrderId() {
        String orderId = "123";

        return orderId;
    }

    private Map<String, String> getOrderList() {
        Map<String, String> orderList = new HashMap<>();
        String orderId = "123";
        orderList.put("orderId",orderId);

        return orderList;
    }
}