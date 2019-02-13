package com.wlailton.firewarningapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wlailton.firewarningapi.models.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

}
