package com.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@javax.persistence.Table(name = "suborders", schema = "public", catalog = "PocketOrder")
public class Suborder implements Serializable {
    private int id;
    private Order order;
    private List<Product> products;

    private SuborderType type;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Suborder suborder = (Suborder) o;

        if (id != suborder.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "order_fk", referencedColumnName = "id", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToMany
    @JoinTable(name = "products_suborders", catalog = "PocketOrder", schema = "public", joinColumns = @JoinColumn(name = "suborder_fk", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "product_fk", referencedColumnName = "id", nullable = false))
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @ManyToOne
    @JoinColumn(name = "type_fk", referencedColumnName = "id", nullable = false)
    public SuborderType getType() {
        return type;
    }

    public void setType(SuborderType type) {
        this.type = type;
    }
}
