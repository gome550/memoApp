/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.memoapp.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 07930
 */
@Entity
@Table(name = "MEMO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memo.findAll", query = "SELECT m FROM Memo m"),
    @NamedQuery(name = "Memo.findById", query = "SELECT m FROM Memo m WHERE m.id = :id"),
    @NamedQuery(name = "Memo.findByMemo", query = "SELECT m FROM Memo m WHERE m.memo = :memo")})
public class Memo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 512)
    @Column(name = "MEMO")
    private String memo;

    public Memo() {
    }

    public Memo(Integer id) {
	this.id = id;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getMemo() {
	return memo;
    }

    public void setMemo(String memo) {
	this.memo = memo;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Memo)) {
	    return false;
	}
	Memo other = (Memo) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "sample.javaee.memoapp.entity.Memo[ id=" + id + " ]";
    }
    
}
