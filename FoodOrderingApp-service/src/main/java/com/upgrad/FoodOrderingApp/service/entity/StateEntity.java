package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@NamedQueries(
        @NamedQuery(
                name="getStateByUUID",
                query="SELECT s FROM StateEntity s WHERE s.uuid LIKE :uuid"
        )
)
@Table(name = "state")
public class StateEntity implements Serializable {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NotNull
  private int id;

  @Column(name = "uuid")
  @NotNull
  @Size(max = 200)
  private String uuid;

  @Column(name = "state_name")
  @NotNull
  @Size(max = 30)
  private String stateName;

  public StateEntity() {}

  public StateEntity(String uuid, String stateName) {
    this.uuid = uuid;
    this.stateName = stateName;
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

  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }
}
