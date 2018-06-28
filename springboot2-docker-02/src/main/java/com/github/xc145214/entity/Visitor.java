package com.github.xc145214.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author: xiac
 * @date: 2018/6/28
 * @desc: 访问实体。
 */
@Entity
public class Visitor {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private  long  times;
    @Column(nullable = false)
    private  String  ip;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", times=" + times +
                ", ip='" + ip + '\'' +
                '}';
    }
}
