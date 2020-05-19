package com.hakalab.api.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//@Entity
@Embeddable
public class BaseEntity {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_base")
//	private Integer idBase;
	
	@CreationTimestamp
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar creationDate;
	
	@UpdateTimestamp
	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar updatedDate;

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	public Calendar getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Calendar updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
