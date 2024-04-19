package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

import java.util.ArrayList;
import java.util.List;

public class ArrayNode extends JsonTemplateNode<List<JsonTemplateNode<?>>> {
	
	public ArrayNode(List<JsonTemplateNode<?>> values, String type, int start, int end) {
		super(type, start, end);
		this.setValue(values);
	}
	
	public ArrayNode(String type, int start, int end) {
		this(new ArrayList<>(), type, start, end);
	}
	
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.ARRAY;
	}
	
	public void add(JsonTemplateNode<?> node) {
		this.getValue().add(node);
	}
	
}
