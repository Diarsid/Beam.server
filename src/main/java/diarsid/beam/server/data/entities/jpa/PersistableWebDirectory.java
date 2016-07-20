/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.entities.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author Diarsid
 */

@Entity
@Table(name = "dirs")
public class PersistableWebDirectory implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "dir_id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private PersistableUser user;
    
    @Column(name = "dir_name")
    private String dirName;
    
    @Column(name = "dir_order")
    private int dirOrder;
    
    @Column(name = "dir_place")
    private String dirPlace;
    
    @OneToMany(
            mappedBy = "dir",
            fetch = FetchType.EAGER, 
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @OrderBy("page_order")
    private List<PersistableWebPage> dirPages;
    
    public PersistableWebDirectory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersistableUser getUser() {
        return user;
    }

    public void setUser(PersistableUser user) {
        this.user = user;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public int getDirOrder() {
        return dirOrder;
    }

    public void setDirOrder(int dirOrder) {
        this.dirOrder = dirOrder;
    }

    public String getDirPlace() {
        return dirPlace;
    }

    public void setDirPlace(String dirPlace) {
        this.dirPlace = dirPlace;
    }

    public List<PersistableWebPage> getDirPages() {
        return dirPages;
    }

    public void setDirPages(List<PersistableWebPage> dirPages) {
        this.dirPages = dirPages;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.user);
        hash = 59 * hash + Objects.hashCode(this.dirName);
        hash = 59 * hash + this.dirOrder;
        hash = 59 * hash + Objects.hashCode(this.dirPlace);
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
        if ( this.dirOrder != other.dirOrder ) {
            return false;
        }
        if ( !Objects.equals(this.dirName, other.dirName) ) {
            return false;
        }
        if ( !Objects.equals(this.dirPlace, other.dirPlace) ) {
            return false;
        }
        if ( !Objects.equals(this.user, other.user) ) {
            return false;
        }
        return true;
    }
}
