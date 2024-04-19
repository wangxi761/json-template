package org.codxiii.json.template.ast;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseNode {
	private String type;
	private int start;
	private int end;
	
	public BaseNode(String type, int start, int end) {
		this.type = type;
		this.start = start;
		this.end = end;
	}
	
}
