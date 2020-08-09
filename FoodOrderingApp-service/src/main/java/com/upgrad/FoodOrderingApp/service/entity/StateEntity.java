package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "state", schema = "public", catalog = "restaurantdb")
public class StateEntity {
    private int id;
    private String uuid;
    private String stateName;
    private Collection<AddressEntity> addressesById;

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
    @Column(name = "state_name", nullable = true, length = 30)
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateEntity that = (StateEntity) o;
        return id == that.id &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, stateName);
    }

    @OneToMany(mappedBy = "stateByStateId")
    public Collection<AddressEntity> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressEntity> addressesById) {
        this.addressesById = addressesById;
    }
}
