package com.wlailton.firewarningapi.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.JoinFormula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wlailton.firewarningapi.enums.DangerLevel;
import com.wlailton.firewarningapi.enums.Status;

@Entity
@JsonInclude(Include.NON_NULL)
public class Company {

	@Id
	@JsonIgnore
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	@NotNull
	private String cnpj;

	@NotNull
	@Size(min = 3, max = 250)
	private String fantasyName;

	@Column
	private String contact;
 
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	@JsonIgnore
    private Set<Incident> incidents;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinFormula("(SELECT i.id FROM incident i WHERE i.company_id = id ORDER BY i.id DESC LIMIT 1)")
	@JsonIgnore
    private Incident latestIncident;

	public Incident getLatestIncident() {
		return latestIncident;
	}

	public void setLatestIncident(Incident latestIncident) {
		this.latestIncident = latestIncident;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;

	}

	public String getFantasyName() {
		return fantasyName;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Set<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(Set<Incident> incidents) {
		this.incidents = incidents;
	}
	
	public DangerLevel getDangerLevel() {
		return latestIncident != null ? latestIncident.getDangerLevel() : null; 
	}
	
	public Status getStatus() {
		return latestIncident != null ? latestIncident.getStatus() : null; 
	}
	
	public String getComment() {
		return latestIncident != null ? latestIncident.getComment() : null; 
	}
	
	
}
