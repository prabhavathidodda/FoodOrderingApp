package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "address", schema = "public", catalog = "restaurantdb")
public class AddressEntity {
    private int id;
    private String uuid;
    private String flatBuilNumber;
    private String locality;
    private String city;
    private String pincode;
    private Integer stateId;
    private Integer active;
    private StateEntity stateByStateId;
    private Collection<CustomerAddressEntity> customerAddressesById;
    private Collection<OrdersEntity> ordersById;
    private Collection<RestaurantEntity> restaurantsById;

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
    @Column(name = "flat_buil_number", nullable = true, length = 255)
    public String getFlatBuilNumber() {
        return flatBuilNumber;
    }

    public void setFlatBuilNumber(String flatBuilNumber) {
        this.flatBuilNumber = flatBuilNumber;
    }

    @Basic
    @Column(name = "locality", nullable = true, length = 255)
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 30)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "pincode", nullable = true, length = 30)
    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Basic
    @Column(name = "state_id", nullable = true)
    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return id == that.id &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(flatBuilNumber, that.flatBuilNumber) &&
                Objects.equals(locality, that.locality) &&
                Objects.equals(city, that.city) &&
                Objects.equals(pincode, that.pincode) &&
                Objects.equals(stateId, that.stateId) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, flatBuilNumber, locality, city, pincode, stateId, active);
    }

    @ManyToOne
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    public StateEntity getStateByStateId() {
        return stateByStateId;
    }

    public void setStateByStateId(StateEntity stateByStateId) {
        this.stateByStateId = stateByStateId;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<CustomerAddressEntity> getCustomerAddressesById() {
        return customerAddressesById;
    }

    public void setCustomerAddressesById(Collection<CustomerAddressEntity> customerAddressesById) {
        this.customerAddressesById = customerAddressesById;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<RestaurantEntity> getRestaurantsById() {
        return restaurantsById;
    }

    public void setRestaurantsById(Collection<RestaurantEntity> restaurantsById) {
        this.restaurantsById = restaurantsById;
    }
}
