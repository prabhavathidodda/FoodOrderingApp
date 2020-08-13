package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "customer_auth", schema = "public", catalog = "restaurantdb")
@NamedQueries(
        {
                @NamedQuery(name = "getCustomerAuthToken", query = "select c from CustomerAuthEntity c where c.accessToken = :accessToken"),

        }
)
public class CustomerAuthEntity {
    private int id;
    private String uuid;
    private int customerId;
    private String accessToken;
    private ZonedDateTime loginAt;
    private ZonedDateTime logoutAt;
    private ZonedDateTime expiresAt;
    private CustomerEntity customerEntity;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
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
    public ZonedDateTime getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(ZonedDateTime loginAt) {
        this.loginAt = loginAt;
    }

    @Basic
    @Column(name = "logout_at", nullable = true)
    public ZonedDateTime getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(ZonedDateTime logoutAt) {
        this.logoutAt = logoutAt;
    }

    @Basic
    @Column(name = "expires_at", nullable = true)
    public ZonedDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(ZonedDateTime expiresAt) {
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
    public CustomerEntity getCustomer() {
        return customerEntity;
    }

    public void setCustomer(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }
}
