package com.library.librarymanagement.response;

import lombok.Data;

@Data
public class Status {
	
	String code;
	String message;
	
	
	public Status(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	

}
