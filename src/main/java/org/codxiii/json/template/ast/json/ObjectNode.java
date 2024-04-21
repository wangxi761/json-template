package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectNode extends JsonTemplateNode<LinkedHashMap<TextNode, JsonTemplateNode<?>>> implements Iterable<Map.Entry<TextNode, JsonTemplateNode<?>>> {
	public ObjectNode(LinkedHashMap<TextNode, JsonTemplateNode<?>> value, int start, int end) {
		super(start, end);
		this.setValue(value);
	}
	
	public ObjectNode(int start, int end) {
		this(new LinkedHashMap<>(), start, end);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.OBJECT;
	}
	
	public void put(TextNode key, JsonTemplateNode<?> node) {
		this.getValue().put(key, node);
	}
	
	@Override
	public Iterator<Map.Entry<TextNode, JsonTemplateNode<?>>> iterator() {
		return this.getValue().entrySet().iterator();
	}
	
	public int size() {
		return this.getValue().size();
	}
	
	public boolean isEmpty() {
		return this.getValue().isEmpty();
	}
}
