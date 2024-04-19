package org.codxiii.json.template.ast;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class JsonTemplateNode<T> extends BaseNode {
	
	private T value;
	
	public JsonTemplateNode(String type, int start, int end) {
		super(type, start, end);
	}
	
	public abstract JsonTemplateNodeType getNodeType();
	

}
