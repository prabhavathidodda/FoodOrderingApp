package com.upgrad.FoodOrderingApp.service.entity;

import com.upgrad.FoodOrderingApp.service.dao.CategoryDAO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
<<<<<<< HEAD
@Table(name = "category")
public class CategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
=======
@Table(name="category")
@NamedQueries({
        @NamedQuery(name="getCategoryByUUId", query = "SELECT c FROM CategoryEntity c WHERE c.uuid = :uuid"),
        @NamedQuery(name="getAllCategoriesOrderedByName", query = "SELECT c from CategoryEntity c ORDER BY c.categoryname ASC")
})
public class CategoryEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> master
    private Integer id;

    @Column(name = "uuid")
    @NotNull
    @Size(max = 200)
    private String uuid;

    @Column(name = "category_name")
    @Size(max = 255)
<<<<<<< HEAD
    private String categoryName;

    public CategoryEntity() {
    }
=======
    private String categoryname;
>>>>>>> master

    @ManyToMany
    @JoinTable(name = "category_item", joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<ItemEntity> items = new ArrayList<>();

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

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
<<<<<<< HEAD
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
=======
    public String getCategoryname() {
=======
    public String getCategoryName() {
>>>>>>> prabha/paymentcontroller
        return categoryname;
    }

    public void setCategoryName(String categoryname) {
        this.categoryname = categoryname;
>>>>>>> master
    }
}
