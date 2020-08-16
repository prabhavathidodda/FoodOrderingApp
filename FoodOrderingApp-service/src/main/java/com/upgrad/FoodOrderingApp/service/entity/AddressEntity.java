package com.upgrad.FoodOrderingApp.service.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
<<<<<<< HEAD
import javax.validation.constraints.NotNull;
=======
>>>>>>> master
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "address")
<<<<<<< HEAD
@NamedQueries({
//        @NamedQuery(
//                name = "deletedSavedAddresses",
//                query = "DELETE FROM address a WHERE a.uuid LIKE :uuid"
//        ),
//        @NamedQuery(
//                name = "getStateByUUID",
//                query = "SELECT a FROM address a WHERE a.uuid LIKE :uuid"
//        ),
        @NamedQuery(name = "getAddressByUuid", query = "SELECT a from AddressEntity a where a.uuid = :uuid")
})

public class AddressEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    @NotNull
=======
@NamedQueries(
        {
                @NamedQuery(name = "getAddressById", query = "select a from AddressEntity a where a.id=:id")
        }
)

public class AddressEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid")
>>>>>>> master
    @Size(max = 200)
    private String uuid;

    @Column(name = "flat_buil_number")
    @Size(max = 255)
<<<<<<< HEAD
    private String flatBuilNumber;
=======
    private String flatBuildingNumber;
>>>>>>> master

    @Column(name = "locality")
    @Size(max = 255)
    private String locality;

    @Column(name = "city")
    @Size(max = 30)
    private String city;

    @Column(name = "pincode")
    @Size(max = 30)
<<<<<<< HEAD
    private String pinCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "state_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private StateEntity state;

    @Column(name = "active")
    private int active;

    public AddressEntity() {
        active = 1;
    }
=======
    private String pincode;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "state_id")
    private StateEntity state;

    @Column(name = "active")
    private Integer active;
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
    public String getFlatBuilNumber() {
        return flatBuilNumber;
    }

    public void setFlatBuilNumber(String flatBuilNumber) {
        this.flatBuilNumber = flatBuilNumber;
=======
    public String getFlatBuildingNumber() {
        return flatBuildingNumber;
    }

    public void setFlatBuildingNumber(String flatBuildingNumber) {
        this.flatBuildingNumber = flatBuildingNumber;
>>>>>>> master
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

<<<<<<< HEAD
    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pincode) {
        this.pinCode = pincode;
=======
    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
>>>>>>> master
    }

    public StateEntity getState() {
        return state;
    }

    public void setState(StateEntity state) {
        this.state = state;
    }

<<<<<<< HEAD
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
=======
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
>>>>>>> master
