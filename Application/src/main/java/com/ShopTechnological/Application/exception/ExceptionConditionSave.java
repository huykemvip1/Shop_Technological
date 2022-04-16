package com.ShopTechnological.Application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Field constaint")
public class ExceptionConditionSave extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExceptionConditionSave(String message) {
        super(message);
    }
}
