package com.driver;

import java.util.List;

public class Order {

    private String id;
    private int deliveryTime;

//    public Order() {
    //  OrderController.java OrderService.java  OrderRepository.java  Order.java  DeliveryPartner.java
//    }

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        List<String> lst= List.of(deliveryTime.split(":"));
        this.deliveryTime=(Integer.parseInt(lst.get(0))*60)+Integer.parseInt(lst.get(1));
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
