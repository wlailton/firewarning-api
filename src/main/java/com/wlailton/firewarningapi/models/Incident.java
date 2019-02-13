package com.wlailton.firewarningapi.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wlailton.firewarningapi.enums.DangerLevel;
import com.wlailton.firewarningapi.enums.Status;

@Entity
@JsonInclude(Include.NON_NULL)
public class Incident {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private DangerLevel dangerLevel;

	@Size(min = 3, max = 250)
	private String comment;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Temporal(TemporalType.TIMESTAMP)
	private Date DateResolution;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
	@JsonBackReference
    private Company company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DangerLevel getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(DangerLevel dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDateResolution() {
		return DateResolution;
	}

	public void setDateResolution(Date dateResolution) {
		DateResolution = dateResolution;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	

}
