package org.codxiii.json.template.ast;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class JsonTemplateNode<T> extends BaseNode {
	
	private T value;
	
	public JsonTemplateNode(int start, int end) {
		super(null, start, end);
		setType(getNodeType().name());
	}
	
	public abstract JsonTemplateNodeType getNodeType();
	
	public String toRawString() {
		return getValue().toString();
	}
	
}
