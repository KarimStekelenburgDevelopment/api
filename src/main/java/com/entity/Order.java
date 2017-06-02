package com.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.*;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@javax.persistence.Table(name = "orders", schema = "public", catalog = "PocketOrder")
public class Order implements Serializable {
    private int id;
    private Timestamp timeofplacement;
    private Table table;
    private OrderStatus status;
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
    @Column(name = "timeofplacement", nullable = false)
    public Timestamp getTimeofplacement() {
        return timeofplacement;
    }

    public void setTimeofplacement(Timestamp timeofplacement) {
        this.timeofplacement = timeofplacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (timeofplacement != null ? !timeofplacement.equals(order.timeofplacement) : order.timeofplacement != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (timeofplacement != null ? timeofplacement.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "table_fk", referencedColumnName = "area_fk", nullable = false)
    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @ManyToOne
    @JoinColumn(name = "status_fk", referencedColumnName = "id", nullable = false)
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @OneToMany(mappedBy = "order")
    public List<Suborder> getSuborders() {
        return suborders;
    }

    public void setSuborders(List<Suborder> suborders) {
        this.suborders = suborders;
    }
}
