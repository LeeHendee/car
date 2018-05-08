package com.jixing.kd.mall.entity;

/**
 * Author ：LeeHang
 * CreateTime ：2018/1/5.
 * Used to :
 */

public class SingleItemEntity {
    String title;

    String price;

    String count;

    @Override
    public String toString() {
        return "SingleItem{" +
                "title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
