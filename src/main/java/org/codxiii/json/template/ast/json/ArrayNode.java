package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayNode extends JsonTemplateNode<List<JsonTemplateNode<?>>> implements Iterable<JsonTemplateNode<?>>{
	
	public ArrayNode(List<JsonTemplateNode<?>> values, int start, int end) {
		super(start, end);
		this.setValue(values);
	}
	
	public ArrayNode(String type, int start, int end) {
		this(new ArrayList<>(), start, end);
	}
	
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.ARRAY;
	}
	
	public void add(JsonTemplateNode<?> node) {
		this.getValue().add(node);
	}
	
	@Override
	public Iterator<JsonTemplateNode<?>> iterator() {
		return this.getValue().iterator();
	}
	
	public boolean isEmpty() {
		return this.getValue().isEmpty();
	}
	
	public int size() {
		return this.getValue().size();
	}
}
