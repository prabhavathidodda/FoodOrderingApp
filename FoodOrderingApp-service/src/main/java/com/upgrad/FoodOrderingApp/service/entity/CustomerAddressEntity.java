package com.upgrad.FoodOrderingApp.service.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "customer_address")
@NamedQueries({
  @NamedQuery(
      name = "getAllCustomerAddressByCustomer",
      query =
          "SELECT c from CustomerAddressEntity c where c.customer = :customer_entity AND c.address.active = :active"),
  @NamedQuery(
      name = "getCustomerAddressByAddress",
      query = "SELECT c from CustomerAddressEntity c where c.address = :address_entity")
})
public class CustomerAddressEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "address_id")
  @OnDelete(action = OnDeleteAction.NO_ACTION)
  @NotNull
  private AddressEntity address;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @NotNull
  private CustomerEntity customer;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CustomerEntity getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerEntity customer) {
    this.customer = customer;
  }

  public AddressEntity getAddress() {
    return address;
  }

  public void setAddress(AddressEntity address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "CustomerAddressEntity{"
        + "id="
        + id
        + ", address="
        + address
        + ", customer="
        + customer
        + '}';
  }
}
