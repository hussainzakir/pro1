package com.repsrv.csweb.core.model.account.imports;

import java.lang.reflect.Field;

import javax.validation.ConstraintViolation;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import com.poiji.annotation.ExcelRow;


public abstract class Row {

	private RowError errors;
	
	@JsonProperty("Row Index")
	@ExcelRow
    private int rowIndex;
	
	public abstract String getKey();
	
	public abstract String getTabName();
	
	public abstract String getKeyName();

	public int getRowIndex() {
		return rowIndex + 1;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	public RowError addViolation(ConstraintViolation<?> violation) {
		if(errors == null) {
			errors = new RowError();
			errors.setRow(this.rowIndex);
		}
			
		errors.addError(violation.getMessage());
		
		return errors;
	}
	
	public RowError getRowErrors() {
		return this.errors;
	}
	
	public abstract String getParentKey();
	
	public void correctNullStrings() throws BeansException, IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = this.getClass();
	    Field[] fields = clazz.getDeclaredFields();
	    PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(this);

	    for(Field field : fields) {
	    	String propName = field.getName();
	    	if (String.class.equals(field.getType()) 
	    			&& myAccessor.isWritableProperty(propName)
	    			&& myAccessor.isReadableProperty(propName)
	    			&& myAccessor.getPropertyValue(propName) == null) {
	    		myAccessor.setPropertyValue(propName, "");
	    	}
	    }
	}
}
