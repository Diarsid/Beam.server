/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.entities.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author Diarsid
 */

@Entity
@Table(name = "pages")
public class PersistableWebPage implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "page_id")
    private int pageId;
    
    @Column(name = "page_url")
    private String url;    
    
    @Column(name = "page_name")
    private String name;
    
    @Column(name = "page_order")
    private int pageOrder;
         
    @ManyToOne
    @JoinColumn(
            name = "dir_id"
//            , 
//            referencedColumnName = "dir_id",
//            insertable = true
    )
    private PersistableWebDirectory dir;
    
    public PersistableWebPage() {        
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }
    
    public PersistableWebDirectory getDir() {
        return dir;
    }

    public void setDir(PersistableWebDirectory dir) {
        this.dir = dir;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getPageOrder() {
        return this.pageOrder;
    }
        
    public void setPageOrder(int newOrder) {
        this.pageOrder = newOrder;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.url);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + this.pageOrder;
        hash = 67 * hash + Objects.hashCode(this.dir);
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
        final PersistableWebPage other = ( PersistableWebPage ) obj;
        if ( this.pageOrder != other.pageOrder ) {
            return false;
        }
        if ( ! Objects.equals(this.url, other.url) ) {
            return false;
        }
        if ( ! Objects.equals(this.name, other.name) ) {
            return false;
        }
        if ( ! Objects.equals(this.dir, other.dir) ) {
            return false;
        }
        return true;
    }
    
}
