/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.entities.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import diarsid.beam.server.data.entities.OrderableWebItem;
import diarsid.beam.server.data.entities.OrderableWebItemCollection;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author Diarsid
 */

@Entity
@Table(name = "dirs")
public class PersistableWebDirectory 
        implements Serializable, 
                   OrderableWebItem, 
                   OrderableWebItemCollection {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "dir_id")
    private int id;
    
    @ManyToOne(cascade = {DETACH, MERGE, PERSIST, REFRESH})
    @JoinColumn(name = "user_id")
    private PersistableUser user;
    
    @Column(name = "dir_name")
    private String name;
    
    @Column(name = "dir_order")
    private int order;
    
    @Column(name = "dir_place")
    private String place;
    
    @OneToMany(
            mappedBy = "dir",
            fetch = FetchType.EAGER, 
            cascade = ALL,
            orphanRemoval = true)
    @OrderBy("page_order")
    private List<PersistableWebPage> pages;
    
    public PersistableWebDirectory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersistableUser getUser() {
        return this.user;
    }

    public void setUser(PersistableUser user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public int getOrder() {
        return order;
    }
    
    @Override
    public List<? extends OrderableWebItem> getItems() {
        return (List<? extends OrderableWebItem>) this.pages;
    }

    public String getPlace() {
        return place;
    }

    public List<PersistableWebPage> getPages() {
        return pages;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public void setOrder(int order) {
        this.order = order;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPages(List<PersistableWebPage> pages) {
        this.pages = pages;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.user);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + this.order;
        hash = 59 * hash + Objects.hashCode(this.place);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final PersistableWebDirectory other = ( PersistableWebDirectory ) obj;
        if ( this.order != other.order ) {
            return false;
        }
        if ( !Objects.equals(this.name, other.name) ) {
            return false;
        }
        if ( !Objects.equals(this.place, other.place) ) {
            return false;
        }
        if ( !Objects.equals(this.user, other.user) ) {
            return false;
        }
        return true;
    }
}
