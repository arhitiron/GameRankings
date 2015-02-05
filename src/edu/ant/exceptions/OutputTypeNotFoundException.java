package edu.ant.exceptions;

public class OutputTypeNotFoundException extends Exception {
	
	public OutputTypeNotFoundException() { 
		super(); 
	}
	
	public OutputTypeNotFoundException(String message) { 
		super(message); 
	}
	
	public OutputTypeNotFoundException(String message, Throwable cause) { 
		super(message, cause); 
	}
	
	public OutputTypeNotFoundException(Throwable cause) { 
		super(cause); 
	}
}
