package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "coupon", schema = "public", catalog = "restaurantdb")
public class CouponEntity {
    private int id;
    private String uuid;
    private String couponName;
    private int percent;
    private Collection<OrdersEntity> ordersById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uuid", nullable = false, length = 200)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "coupon_name", nullable = true, length = 255)
    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    @Basic
    @Column(name = "percent", nullable = false)
    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponEntity that = (CouponEntity) o;
        return id == that.id &&
                percent == that.percent &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(couponName, that.couponName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, couponName, percent);
    }

    @OneToMany(mappedBy = "couponByCouponId")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
