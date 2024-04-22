package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.ast.Renderable;

import java.util.Map;

public class VarNode extends JsonTemplateNode<String> implements Renderable {
	
	public VarNode(String variableName, int start, int end) {
		super(start, end);
		this.setValue(variableName);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.VAR;
	}
	
	@Override
	public String toRawString() {
		return "${" + super.toRawString() + "}";
	}
	
	
	@Override
	public String render(Map<String, Object> binding) {
		if (!binding.containsKey(this.getValue())) {
			return this.toRawString();
		}
		return binding.get(this.getValue()).toString();
	}
}
