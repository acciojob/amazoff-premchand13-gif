package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    private HashMap<String,Order> orderHashMap=new HashMap<>();
    private HashMap<String ,DeliveryPartner> deliveryPartnerHashMap=new HashMap<>();
    private HashMap<String , List<String> > orderPartnerPairHashMap=new HashMap<>();
    private HashMap<String,Boolean> orderAssignStatus=new HashMap<>();


    public void addOrder(Order order){
        if(order==null) return;
        orderAssignStatus.put(order.getId(),false);
        orderHashMap.put(order.getId(),order);
    }
    public void addDeliveryPartner(String id){
        DeliveryPartner deliveryPartner=new DeliveryPartner(id);
        deliveryPartnerHashMap.put(id,deliveryPartner);
    }
    public void addPair(String orderId,String id){
        if(orderPartnerPairHashMap.containsKey(id)){
            List<String> lst=orderPartnerPairHashMap.get(id);
            lst.add(orderId);
            deliveryPartnerHashMap.get(id).setNumberOfOrders(lst.size());
            orderPartnerPairHashMap.put(id,lst);
        }
        else{
            List<String> lst=new ArrayList<>();
            lst.add(orderId);
            deliveryPartnerHashMap.get(id).setNumberOfOrders(lst.size());
            orderPartnerPairHashMap.put(id,lst);
        }
        orderAssignStatus.put(orderId,true);
    }
    public  Order getOrderById(String id){
        if(orderHashMap.containsKey(id)){
            return orderHashMap.get(id);
        }
        return null;
    }
    public DeliveryPartner getPartnerById(String id){
        if(deliveryPartnerHashMap.containsKey(id)){
            return deliveryPartnerHashMap.get(id);
        }
        return null;
    }
    public int getOrderCountByPartner(String id){
        if(deliveryPartnerHashMap.containsKey(id)){
            return deliveryPartnerHashMap.get(id).getNumberOfOrders();
        }
        return 0;
    }
    public List<String> getListOfOrders(String id){
        if(orderPartnerPairHashMap.containsKey(id)){
            return orderPartnerPairHashMap.get(id);
        }
        return null;
    }
    public List<String> getAllOrders(){
//        List<String> lst=new ArrayList<>(orderHashMap.keySet());
//        for(String s:orderHashMap.keySet()){
//            lst.add(s);
//        }
        if(orderHashMap.isEmpty()){
            return null;
        }
        return new ArrayList<>(orderHashMap.keySet());
    }
    public int getUnassignedOrderCount(){
        int count=0;
        for(String s:orderAssignStatus.keySet()){
            if(!orderAssignStatus.get(s)){
                count++;
            }
        }
        return count;
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String deliveryTime,String id){
        int count=0;
        int time;
        List<String> lst= List.of(deliveryTime.split(":"));
        time=(Integer.parseInt(lst.get(0))*60)+Integer.parseInt(lst.get(1));
        List<String> orderLst=orderPartnerPairHashMap.get(id);
        for(String s:orderLst){
            if(orderHashMap.get(s).getDeliveryTime()>time){
                count++;
            }
        }
        return count;
    }
    public String getLastDeliveryTimeByPartnerId(String id){
        int max=-1;
        List<String> lst=orderPartnerPairHashMap.get(id);
        if(lst.isEmpty()) return null;
        for(String s:lst){
            max=Math.max(max,orderHashMap.get(s).getDeliveryTime());
        }
        int mm=max%60;
        int hh=max/60;
        String h=""+hh,m=""+mm;
        if(hh<10){
           h="0"+hh;
        }
        if(mm<10){
            m="0"+mm;
        }
        return h+":"+m;
    }
    public void deletePartnerById(String id){
        List<String> lst=orderPartnerPairHashMap.get(id);
        for(String s:lst){
            orderAssignStatus.put(s,false);
        }
        deliveryPartnerHashMap.remove(id);
        orderPartnerPairHashMap.remove(id);
    }
    public void deleteOrderById(String id){
        orderHashMap.remove(id);
        orderAssignStatus.remove(id);
        for(String s:orderPartnerPairHashMap.keySet()){
            List<String> lst=orderPartnerPairHashMap.get(s);
            if(lst.contains(id)){
                lst.remove(id);
                deliveryPartnerHashMap.get(s).setNumberOfOrders(lst.size());

                return;
            }
        }
    }

}
