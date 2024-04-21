package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

import java.util.HashMap;
import java.util.Map;

public class ObjectNode extends JsonTemplateNode<Map<TextNode, JsonTemplateNode<?>>> {
	public ObjectNode(Map<TextNode, JsonTemplateNode<?>> value, int start, int end) {
		super(start, end);
		this.setValue(value);
	}
	
	public ObjectNode(int start, int end) {
		this(new HashMap<>(), start, end);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.OBJECT;
	}
	
	public void put(TextNode key, JsonTemplateNode<?> node) {
		this.getValue().put(key, node);
	}
}
