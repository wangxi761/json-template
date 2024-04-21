package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

public class VarNode extends JsonTemplateNode<String> {
	
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
}
