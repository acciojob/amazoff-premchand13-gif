package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
//    @Autowired
    OrderRepository orderRepository=new OrderRepository();
    //111
    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }
    //222
    public void addDeliveryPartner(String id){
        orderRepository.addDeliveryPartner(id);
    }
    //333
    public void addPair(String orderId,String id){
        orderRepository.addPair(orderId,id);
    }
    //444
    public Order getOrderById(String id){
        return orderRepository.getOrderById(id);
    }
    //555
    public DeliveryPartner getPartnerById(String  id){
        return orderRepository.getPartnerById(id);
    }
    //666
    public int getOrderCountByPartner(String id){
        return orderRepository.getOrderCountByPartner(id);
    }
    //777
    public List<String> getListOfOrders(String id){
        return orderRepository.getListOfOrders(id);
    }
    //888
    public List<String> getAllOrders(){
        return orderRepository.getAllOrders();
    }
    //999
    public int getUnassignedOrderCount(){
        return orderRepository.getUnassignedOrderCount();
    }
    //10
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String id){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,id);
    }
    //111
    public String getLastDeliveryTimeByPartnerId(String id){
        return orderRepository.getLastDeliveryTimeByPartnerId(id);
    }
    //122
    public void deletePartnerById(String id){
        orderRepository.deletePartnerById(id);
    }
    public void deleteOrderById(String id){
        orderRepository.deleteOrderById(id);
    }
}
