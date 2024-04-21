package org.codxiii.json.template.ast;

import lombok.Getter;
import lombok.Setter;
import org.codxiii.json.template.ast.json.ArrayNode;
import org.codxiii.json.template.ast.json.ObjectNode;

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
	
	public ObjectNode toObjectNode() {
		if (this instanceof ObjectNode) {
			return (ObjectNode) this;
		}
		throw new IllegalStateException("Node is not an object node");
	}
	
	public ArrayNode toArrayNode() {
		if (this instanceof ArrayNode) {
			return (ArrayNode) this;
		}
		throw new IllegalStateException("Node is not an array node");
	}
	
	
}
