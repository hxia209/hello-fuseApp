package org.hui.demo.impl;

import org.hui.demo.model.Order;
import org.hui.demo.model.OrderWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static OrderWrapper orders = new OrderWrapper();

    public OrderWrapper getAllOrders() {
        return orders;
    }

    public Order createOrder(String requester, String type, int quantity) {
        Order newOrder = new Order(requester, type, quantity);
        orders.getOrders().add(newOrder);
        return newOrder;
    }

}
