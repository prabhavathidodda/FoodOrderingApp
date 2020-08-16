package com.upgrad.FoodOrderingApp.service.entity;

<<<<<<< HEAD
=======
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

>>>>>>> master
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

<<<<<<< HEAD
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllStates", query = "SELECT s from StateEntity s"),
        @NamedQuery(
                name = "getStateByUuid",
                query = "SELECT s FROM StateEntity s WHERE s.uuid LIKE :uuid"
        )}
)
@Table(name = "state")
public class StateEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
=======
@
        Entity
@Table(name = "state")
@NamedQueries(
        {
                @NamedQuery(name = "getStateById", query = "select s from StateEntity s where s.id=:id")

        }
)

public class StateEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> master
    private Integer id;

    @Column(name = "uuid")
    @NotNull
    @Size(max = 200)
    private String uuid;

    @Column(name = "state_name")
<<<<<<< HEAD
    @NotNull
    @Size(max = 30)
    private String stateName;

    public StateEntity() {
    }

    public StateEntity(String uuid, String stateName) {
        this.uuid = uuid;
        this.stateName = stateName;
    }

=======
    @Size(max = 30)
    private String stateName;

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

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
<<<<<<< HEAD
=======

>>>>>>> master
}
