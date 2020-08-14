package com.upgrad.FoodOrderingApp.service.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "address")
// @NamedQueries({
//        @NamedQuery(
//                name = "getAllSavedAddresses",
//                query = "select u from address")
// })

//@NamedQuery(
//        name="getAllSavedAddresses",
//        query="SELECT c FROM Customer c WHERE c.name LIKE :custName"
//)
public class AddressEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public int id;

  @Column(name = "uuid")
  @NotNull
  @Size(max = 200)
  public String uuid;

  @Column(name = "flat_buil_number")
  @Size(max = 255)
  public String flatBuilNumber;

  @Column(name = "locality")
  @Size(max = 255)
  public String locality;

  @Column(name = "city")
  @Size(max = 30)
  public String city;

  @Column(name = "pincode")
  @Size(max = 30)
  public String pinCode;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "state_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private StateEntity state;

  @Column(name = "active")
  public int active;

  public AddressEntity() {
    active = 1;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public String getFlatBuilNumber() {
    return flatBuilNumber;
  }

  public void setFlatBuilNumber(String flatBuilNumber) {
    this.flatBuilNumber = flatBuilNumber;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPinCode() {
    return pinCode;
  }

  public void setPinCode(String pincode) {
    this.pinCode = pincode;
  }

  public StateEntity getState() {
    return state;
  }

  public void setState(StateEntity state) {
    this.state = state;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }
}
