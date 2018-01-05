package com.example.gtercn.car.mall.entity;

import java.util.List;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/5.
 * Used to : 订单管理实体类
 */

public class OrderEntity {

    String orderNumber;

    String totalCost;

    String countAll;

    List<SingleItemEntity> singleItemList;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getCountAll() {
        return countAll;
    }

    public void setCountAll(String countAll) {
        this.countAll = countAll;
    }

    public List<SingleItemEntity> getSingleItemList() {
        return singleItemList;
    }

    public void setSingleItemList(List<SingleItemEntity> singleItemList) {
        this.singleItemList = singleItemList;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderNumber='" + orderNumber + '\'' +
                ", totalCost='" + totalCost + '\'' +
                ", countAll='" + countAll + '\'' +
                ", singleItemList=" + singleItemList +
                '}';
    }
}
