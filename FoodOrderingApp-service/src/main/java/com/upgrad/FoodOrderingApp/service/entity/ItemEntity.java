package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "public", catalog = "restaurantdb")
public class ItemEntity {
    private int id;
    private String uuid;
    private String itemName;
    private int price;
    private String type;
    private Collection<CategoryItemEntity> categoryItemsById;
    private Collection<OrderItemEntity> orderItemsById;
    private Collection<RestaurantItemEntity> restaurantItemsById;

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
    @Column(name = "item_name", nullable = false, length = 30)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, itemName, price, type);
    }

    @OneToMany(mappedBy = "itemByItemId")
    public Collection<CategoryItemEntity> getCategoryItemsById() {
        return categoryItemsById;
    }

    public void setCategoryItemsById(Collection<CategoryItemEntity> categoryItemsById) {
        this.categoryItemsById = categoryItemsById;
    }

    @OneToMany(mappedBy = "itemByItemId")
    public Collection<OrderItemEntity> getOrderItemsById() {
        return orderItemsById;
    }

    public void setOrderItemsById(Collection<OrderItemEntity> orderItemsById) {
        this.orderItemsById = orderItemsById;
    }

    @OneToMany(mappedBy = "itemByItemId")
    public Collection<RestaurantItemEntity> getRestaurantItemsById() {
        return restaurantItemsById;
    }

    public void setRestaurantItemsById(Collection<RestaurantItemEntity> restaurantItemsById) {
        this.restaurantItemsById = restaurantItemsById;
    }
}
