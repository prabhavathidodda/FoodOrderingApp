package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "public", catalog = "restaurantdb")
public class CustomerEntity {
    private int id;
    private String uuid;
    private String firstname;
    private String lastname;
    private String email;
    private String contactNumber;
    private String password;
    private String salt;
    private Collection<CustomerAddressEntity> customerAddressesById;
    private Collection<CustomerAuthEntity> customerAuthsById;
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
    @Column(name = "firstname", nullable = false, length = 30)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = true, length = 30)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "contact_number", nullable = false, length = 30)
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt", nullable = false, length = 255)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return id == that.id &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(contactNumber, that.contactNumber) &&
                Objects.equals(password, that.password) &&
                Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, firstname, lastname, email, contactNumber, password, salt);
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<CustomerAddressEntity> getCustomerAddressesById() {
        return customerAddressesById;
    }

    public void setCustomerAddressesById(Collection<CustomerAddressEntity> customerAddressesById) {
        this.customerAddressesById = customerAddressesById;
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<CustomerAuthEntity> getCustomerAuthsById() {
        return customerAuthsById;
    }

    public void setCustomerAuthsById(Collection<CustomerAuthEntity> customerAuthsById) {
        this.customerAuthsById = customerAuthsById;
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
