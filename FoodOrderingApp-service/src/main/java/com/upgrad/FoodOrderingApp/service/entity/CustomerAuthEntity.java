package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "customer_auth", schema = "public", catalog = "restaurantdb")
public class CustomerAuthEntity {
    private int id;
    private String uuid;
    private int customerId;
    private String accessToken;
    private Timestamp loginAt;
    private Timestamp logoutAt;
    private Timestamp expiresAt;
    private CustomerEntity customerByCustomerId;

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
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "access_token", nullable = true, length = 500)
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Basic
    @Column(name = "login_at", nullable = true)
    public Timestamp getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Timestamp loginAt) {
        this.loginAt = loginAt;
    }

    @Basic
    @Column(name = "logout_at", nullable = true)
    public Timestamp getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(Timestamp logoutAt) {
        this.logoutAt = logoutAt;
    }

    @Basic
    @Column(name = "expires_at", nullable = true)
    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Timestamp expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAuthEntity that = (CustomerAuthEntity) o;
        return id == that.id &&
                customerId == that.customerId &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(loginAt, that.loginAt) &&
                Objects.equals(logoutAt, that.logoutAt) &&
                Objects.equals(expiresAt, that.expiresAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, customerId, accessToken, loginAt, logoutAt, expiresAt);
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    public CustomerEntity getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(CustomerEntity customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }
}
