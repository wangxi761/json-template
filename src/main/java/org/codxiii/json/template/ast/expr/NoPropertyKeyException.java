package org.codxiii.json.template.ast.expr;

public class NoPropertyKeyException extends RuntimeException{
	
	private String key;
	
	private String expression;
	
	public NoPropertyKeyException(String key) {
		this.key = key;
	}
}
