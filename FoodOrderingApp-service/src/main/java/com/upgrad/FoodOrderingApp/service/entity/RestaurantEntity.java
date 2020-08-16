package com.upgrad.FoodOrderingApp.service.entity;

<<<<<<< HEAD
=======

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

>>>>>>> master
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
<<<<<<< HEAD

@Entity
@Table(name = "restaurant")
public class RestaurantEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
=======
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="restaurant")
@NamedQueries({
        @NamedQuery(name="getAllRestaurantsByRating",query = "SELECT r FROM RestaurantEntity r ORDER BY r.customerrating DESC"),
        @NamedQuery(name = "getRestaurantByUuid",query = "SELECT r FROM RestaurantEntity r WHERE r.uuid = :uuid")
})
public class RestaurantEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> master
    private Integer id;

    @Column(name = "uuid")
    @NotNull
    @Size(max = 200)
    private String uuid;

    @Column(name = "restaurant_name")
    @NotNull
    @Size(max = 50)
<<<<<<< HEAD
    private String restaurantName;

    @Column(name = "photo_url")
    @Size(max = 255)
    private String photoUrl;

    @Column(name = "customer_rating")
    @NotNull
    private double customerRating;

    @Column(name = "average_price_for_two")
    @NotNull
    private Integer averagePriceForTwo;

    @Column(name = "number_of_customers_rated")
    @NotNull
    private Integer numberOfCustomersRated;

    public RestaurantEntity() {
    }
=======
    private String restaurantname;

    @Column(name = "photo_url")
    @Size(max = 255)
    private String photourl;

    @Column(name = "customer_rating")
    @NotNull
    private Integer customerrating;

    @Column(name = "average_price_for_two")
    @NotNull
    private Integer averagepricefortwo;

    @Column(name = "number_of_customers_rated")
    @NotNull
    private Integer numberofcustomersrated;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "address_id")
    @NotNull
    private AddressEntity address;
>>>>>>> master

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

<<<<<<< HEAD
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(double customerRating) {
        this.customerRating = customerRating;
    }

    public Integer getAveragePriceForTwo() {
        return averagePriceForTwo;
    }

    public void setAveragePriceForTwo(Integer averagePriceForTwo) {
        this.averagePriceForTwo = averagePriceForTwo;
    }

    public Integer getNumberOfCustomersRated() {
        return numberOfCustomersRated;
    }

    public void setNumberOfCustomersRated(Integer numberOfCustomersRated) {
        this.numberOfCustomersRated = numberOfCustomersRated;
=======
    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public Integer getCustomerrating() {
        return customerrating;
    }

    public void setCustomerrating(Integer customerrating) {
        this.customerrating = customerrating;
    }

    public Integer getAveragepricefortwo() {
        return averagepricefortwo;
    }

    public void setAveragepricefortwo(Integer averagepricefortwo) {
        this.averagepricefortwo = averagepricefortwo;
    }

    public Integer getNumberofcustomersrated() {
        return numberofcustomersrated;
    }

    public void setNumberofcustomersrated(Integer numberofcustomersrated) {
        this.numberofcustomersrated = numberofcustomersrated;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
>>>>>>> master
    }
}
