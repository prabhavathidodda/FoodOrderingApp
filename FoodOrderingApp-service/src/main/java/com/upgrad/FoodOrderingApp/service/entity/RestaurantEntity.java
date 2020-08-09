package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "restaurant", schema = "public", catalog = "restaurantdb")
public class RestaurantEntity {
    private int id;
    private String uuid;
    private String restaurantName;
    private String photoUrl;
    private BigInteger customerRating;
    private int averagePriceForTwo;
    private int numberOfCustomersRated;
    private int addressId;
    private Collection<OrdersEntity> ordersById;
    private AddressEntity addressByAddressId;
    private Collection<RestaurantCategoryEntity> restaurantCategoriesById;
    private Collection<RestaurantItemEntity> restaurantItemsById;

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
    @Column(name = "restaurant_name", nullable = false, length = 50)
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Basic
    @Column(name = "photo_url", nullable = true, length = 255)
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Basic
    @Column(name = "customer_rating", nullable = false, precision = 0)
    public BigInteger getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(BigInteger customerRating) {
        this.customerRating = customerRating;
    }

    @Basic
    @Column(name = "average_price_for_two", nullable = false)
    public int getAveragePriceForTwo() {
        return averagePriceForTwo;
    }

    public void setAveragePriceForTwo(int averagePriceForTwo) {
        this.averagePriceForTwo = averagePriceForTwo;
    }

    @Basic
    @Column(name = "number_of_customers_rated", nullable = false)
    public int getNumberOfCustomersRated() {
        return numberOfCustomersRated;
    }

    public void setNumberOfCustomersRated(int numberOfCustomersRated) {
        this.numberOfCustomersRated = numberOfCustomersRated;
    }

    @Basic
    @Column(name = "address_id", nullable = false)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return id == that.id &&
                averagePriceForTwo == that.averagePriceForTwo &&
                numberOfCustomersRated == that.numberOfCustomersRated &&
                addressId == that.addressId &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(restaurantName, that.restaurantName) &&
                Objects.equals(photoUrl, that.photoUrl) &&
                Objects.equals(customerRating, that.customerRating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, restaurantName, photoUrl, customerRating, averagePriceForTwo, numberOfCustomersRated, addressId);
    }

    @OneToMany(mappedBy = "restaurantByRestaurantId")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public AddressEntity getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(AddressEntity addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }

    @OneToMany(mappedBy = "restaurantByRestaurantId")
    public Collection<RestaurantCategoryEntity> getRestaurantCategoriesById() {
        return restaurantCategoriesById;
    }

    public void setRestaurantCategoriesById(Collection<RestaurantCategoryEntity> restaurantCategoriesById) {
        this.restaurantCategoriesById = restaurantCategoriesById;
    }

    @OneToMany(mappedBy = "restaurantByRestaurantId")
    public Collection<RestaurantItemEntity> getRestaurantItemsById() {
        return restaurantItemsById;
    }

    public void setRestaurantItemsById(Collection<RestaurantItemEntity> restaurantItemsById) {
        this.restaurantItemsById = restaurantItemsById;
    }
}
