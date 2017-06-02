package com.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "products", schema = "public", catalog = "PocketOrder")
public class Product implements Serializable {
    private int id;
    private String name;
    private int unitprice;
    private int taxperc;
    private ProductType type;
    private List<Suborder> suborders;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unitprice", nullable = false, precision = 0)
    public int getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }

    @Basic
    @Column(name = "taxperc", nullable = false, precision = 0)
    public int getTaxperc() {
        return taxperc;
    }

    public void setTaxperc(int taxperc) {
        this.taxperc = taxperc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (unitprice != product.unitprice) return false;
        if (taxperc != product.taxperc) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + unitprice;
        result = 31 * result + taxperc;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "type_fk", referencedColumnName = "id", nullable = false)
    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @ManyToMany(mappedBy = "products")
    public List<Suborder> getSuborders() {
        return suborders;
    }

    public void setSuborders(List<Suborder> suborders) {
        this.suborders = suborders;
    }
}
