package com.wlailton.firewarningapi.exceptions;

public class IncidentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IncidentNotFoundException(String cnpj){
		super("Could not find an incident for company with the CNPJ: " + cnpj);
	}

}
