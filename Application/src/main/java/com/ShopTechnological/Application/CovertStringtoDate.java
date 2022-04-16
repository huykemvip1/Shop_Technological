package com.ShopTechnological.Application;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.AttributeConverter;


public class CovertStringtoDate implements AttributeConverter<String,Date>{

	@Override
	public Date convertToDatabaseColumn(String attribute) {
		return Date.valueOf(attribute);
	}

	@Override
	public String convertToEntityAttribute(Date dbData) {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.format(dbData);
		
	}

	
}
