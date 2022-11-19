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
        DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
        deliveryPartner.setNumberOfOrders(0);
        int noOfOrders=0;
        for(String pId: orderDeliveryPartnerHashMap.values()){
            if(pId==partnerId){
                noOfOrders++;
            }
        }
        deliveryPartner.setNumberOfOrders(noOfOrders);
        deliveryPartnerHashMap.put(partnerId,deliveryPartner);
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {

//        for(DeliveryPartner deliveryPartner:deliveryPartnerHashMap.values()){
//            if(deliveryPartner==null){
//                deliveryPartner.setNumberOfOrders(1);
//                deliveryPartnerHashMap.put(partnerId,deliveryPartner);
//                break;
//            }
//          if(deliveryPartner.getId()==partnerId){
//               deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);
//               deliveryPartnerHashMap.put(partnerId,deliveryPartner);
//          }
//        }
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
        for(String str:deliveryPartnerHashMap.keySet()){
            if(str==partnerId){
                return deliveryPartnerHashMap.get(partnerId);
            }
        }
        return null;
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
       // for(Map.Entry<String,String> map:orderDeliveryPartnerHashMap.entrySet()){
        for(String s:orderDeliveryPartnerHashMap.values()){
            if(partnerId==s){
                ordersListbyPartnerId.add(orderDeliveryPartnerHashMap.get(partnerId));
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
//comment
        for(String ordId: orderDeliveryPartnerHashMap.keySet()){
            if(ordId==orderId){
                orderDeliveryPartnerHashMap.remove(orderId);
            }
        }
    }
}
