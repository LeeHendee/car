package com.example.gtercn.car.mall;

/**
 * Author ：LeeHang
 * CreateTime ：2017/12/20.
 * Used to :
 */

public class ClassifyEntity {
    String name;
    int res;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "ClassifyEntity{" +
                "name='" + name + '\'' +
                ", res=" + res +
                '}';
    }
}
