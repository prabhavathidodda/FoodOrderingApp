package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
  @Id
  @Column(name = "id")
  public int id;

  @Column(name = "uuid")
  public String uuid;

  @Column(name = "flat_buil_number")
  public String flatBuilNumber;

  @Column(name = "locality")
  public String locality;

  @Column(name = "city")
  public String city;

  @Column(name = "pincode")
  public String pincode;

  @Column(name = "state_id")
  public int stateId;

  @Column(name = "active")
  public int active;

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

  public String getPincode() {
    return pincode;
  }

  public void setPincode(String pincode) {
    this.pincode = pincode;
  }

  public int getStateId() {
    return stateId;
  }

  public void setStateId(int stateId) {
    this.stateId = stateId;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
  }
}
