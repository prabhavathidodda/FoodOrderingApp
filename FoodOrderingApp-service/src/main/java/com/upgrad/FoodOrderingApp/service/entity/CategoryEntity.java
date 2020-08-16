package com.upgrad.FoodOrderingApp.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
        @NamedQuery(name="getAllCategories", query = "select c from CategoryEntity c"),
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
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
>>>>>>> master
    }
}
