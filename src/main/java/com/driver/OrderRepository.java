package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {
    HashMap<String,Order> orderHashMap=new HashMap<>();
    HashMap<String,DeliveryPartner> deliveryPartnerHashMap=new HashMap<>();
    HashMap<String,String> orderDeliveryPartnerHashMap=new HashMap<>(); //order partner

    public void addOrder(Order order) {
        orderHashMap.put(order.getId(), order);
    }

    public void addPartner(String partnerId) {
        deliveryPartnerHashMap.put(partnerId,null);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        orderDeliveryPartnerHashMap.put(orderId,partnerId);
    }

    public Order getOrderById(String orderId) {

        //for(Map.Entry<String,Order> map:orderHashMap.){
//        for(String order:orderHashMap.keySet()){
//                if(order==orderId){
//                    return orderHashMap.get(orderId);
//                }
//        }
        return orderHashMap.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return deliveryPartnerHashMap.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        for(Map.Entry<String,DeliveryPartner> map: deliveryPartnerHashMap.entrySet()){
            if(map.getKey()==partnerId){
                return map.getValue().getNumberOfOrders();
            }
        }
        return 0;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> ordersListbyPartnerId=new ArrayList<>();
        for(Map.Entry<String,String> map:orderDeliveryPartnerHashMap.entrySet()){
            if(partnerId==map.getValue()){
                ordersListbyPartnerId.add(map.getValue());
            }
        }
        return ordersListbyPartnerId;
    }

    public List<String> getAllOrders() {
        List<String> orderList=new ArrayList<>();
        for(Order order: orderHashMap.values()){
            orderList.add(order.getId());
        }
        return orderList;
    }

    public Integer getCountOfUnassignedOrders() {
        int count=0;

        for(String str:orderHashMap.keySet()){
            if(!orderDeliveryPartnerHashMap.containsKey(str)){
                count++;
            }
        }
        return Integer.valueOf(count);

    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return null;//remaining
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        Order order=null;
        for(String str1:orderDeliveryPartnerHashMap.values()){
            if(str1==partnerId){
                 order=orderHashMap.get(orderDeliveryPartnerHashMap.get(str1));
            }
        }
        return String.valueOf(order.getDeliveryTime());
    }

    public void deletePartnerById(String partnerId) {
        for(String str2:deliveryPartnerHashMap.keySet()){
            if(str2==partnerId){
                deliveryPartnerHashMap.remove(partnerId);
            }
        }
    }

    public void deleteOrderById(String orderId) {
        for(String ordId:orderHashMap.keySet()){
            if(ordId==orderId){
                orderHashMap.remove(orderId);
            }
        }

        for(String ordId: orderDeliveryPartnerHashMap.keySet()){
            if(ordId==orderId){
                orderDeliveryPartnerHashMap.remove(orderId);
            }
        }
    }
}
