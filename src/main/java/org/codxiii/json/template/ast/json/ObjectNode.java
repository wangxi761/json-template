package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

import java.util.HashMap;
import java.util.Map;

public class ObjectNode extends JsonTemplateNode<Map<String, JsonTemplateNode<?>>> {
	public ObjectNode(Map<String, JsonTemplateNode<?>> value, String type, int start, int end) {
		super(type, start, end);
		this.setValue(value);
	}
	
	public ObjectNode(String type, int start, int end) {
		this(new HashMap<>(), type, start, end);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.OBJECT;
	}
	
	public void put(String key, JsonTemplateNode<?> node) {
		this.getValue().put(key, node);
	}
}
