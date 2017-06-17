package com.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name="getAllTables",
                query="FROM Table as tab ORDER BY tab.id"
        ),

        @NamedQuery(
                name="getByAreaId",
                query="FROM Table where area = :area"
        )
})




@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@javax.persistence.Table(name = "tables", schema = "public", catalog = "PocketOrder")
public class Table implements Serializable {

    private int id;
    private int numofseats;
    private Area area;
    private List<Order> orders;

    @Id
    @SequenceGenerator(name="pk_sequence",sequenceName="tables_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO ,generator="pk_sequence")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "numofseats", nullable = false)
    public int getNumofseats() {
        return numofseats;
    }

    public void setNumofseats(int numofseats) {
        this.numofseats = numofseats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        if (id != table.id) return false;
        if (numofseats != table.numofseats) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + numofseats;
        return result;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "area_fk", referencedColumnName = "id", nullable = false)
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "table", fetch = FetchType.LAZY)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", numofseats=" + numofseats +
                ", area=" + area +
                ", orders=" + orders +
                '}';
    }
}

