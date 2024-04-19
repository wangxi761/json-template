package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

public class VarNode extends JsonTemplateNode<String> {
	
	public VarNode(String variableName, String type, int start, int end) {
		super(type, start, end);
		this.setValue(variableName);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.VAR;
	}
}
