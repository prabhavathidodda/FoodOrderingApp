package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "public", catalog = "restaurantdb")
public class CategoryEntity {
    private int id;
    private String uuid;
    private String categoryName;
    private Collection<CategoryItemEntity> categoryItemsById;
    private Collection<RestaurantCategoryEntity> restaurantCategoriesById;

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
    @Column(name = "category_name", nullable = true, length = 255)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id &&
                Objects.equals(uuid, that.uuid) &&
                Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, categoryName);
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<CategoryItemEntity> getCategoryItemsById() {
        return categoryItemsById;
    }

    public void setCategoryItemsById(Collection<CategoryItemEntity> categoryItemsById) {
        this.categoryItemsById = categoryItemsById;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<RestaurantCategoryEntity> getRestaurantCategoriesById() {
        return restaurantCategoriesById;
    }

    public void setRestaurantCategoriesById(Collection<RestaurantCategoryEntity> restaurantCategoriesById) {
        this.restaurantCategoriesById = restaurantCategoriesById;
    }
}
