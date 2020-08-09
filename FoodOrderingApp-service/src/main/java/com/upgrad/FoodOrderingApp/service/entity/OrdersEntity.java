package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "public", catalog = "restaurantdb")
public class OrdersEntity {
    private int id;
    private String uuid;
    private BigInteger bill;
    private Integer couponId;
    private BigInteger discount;
    private Timestamp date;
    private Integer paymentId;
    private int customerId;
    private int addressId;
    private int restaurantId;
    private Collection<OrderItemEntity> orderItemsById;
    private CouponEntity couponByCouponId;
    private PaymentEntity paymentByPaymentId;
    private CustomerEntity customerByCustomerId;
    private AddressEntity addressByAddressId;
    private RestaurantEntity restaurantByRestaurantId;

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
    @Column(name = "bill", nullable = false, precision = 0)
    public BigInteger getBill() {
        return bill;
    }

    public void setBill(BigInteger bill) {
        this.bill = bill;
    }

    @Basic
    @Column(name = "coupon_id", nullable = true)
    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 0)
    public BigInteger getDiscount() {
        return discount;
    }

    public void setDiscount(BigInteger discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "payment_id", nullable = true)
    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "address_id", nullable = false)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "restaurant_id", nullable = false)
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return id == that.id &&
                customerId == that.customerId &&
                addressId == that.addressId &&
                restaurantId == that.restaurantId &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(bill, that.bill) &&
                Objects.equals(couponId, that.couponId) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(date, that.date) &&
                Objects.equals(paymentId, that.paymentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, bill, couponId, discount, date, paymentId, customerId, addressId, restaurantId);
    }

    @OneToMany(mappedBy = "ordersByOrderId")
    public Collection<OrderItemEntity> getOrderItemsById() {
        return orderItemsById;
    }

    public void setOrderItemsById(Collection<OrderItemEntity> orderItemsById) {
        this.orderItemsById = orderItemsById;
    }

    @ManyToOne
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    public CouponEntity getCouponByCouponId() {
        return couponByCouponId;
    }

    public void setCouponByCouponId(CouponEntity couponByCouponId) {
        this.couponByCouponId = couponByCouponId;
    }

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    public PaymentEntity getPaymentByPaymentId() {
        return paymentByPaymentId;
    }

    public void setPaymentByPaymentId(PaymentEntity paymentByPaymentId) {
        this.paymentByPaymentId = paymentByPaymentId;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    public CustomerEntity getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(CustomerEntity customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public AddressEntity getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(AddressEntity addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    public RestaurantEntity getRestaurantByRestaurantId() {
        return restaurantByRestaurantId;
    }

    public void setRestaurantByRestaurantId(RestaurantEntity restaurantByRestaurantId) {
        this.restaurantByRestaurantId = restaurantByRestaurantId;
    }
}
