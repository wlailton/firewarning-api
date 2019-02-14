package com.wlailton.firewarningapi.exceptions;

public class CompanyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompanyNotFoundException(String cnpj){
		super("Could not find a company with the CNPJ: " + cnpj);
	}
}
