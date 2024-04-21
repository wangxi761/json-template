package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

public class BoolNode extends JsonTemplateNode<Boolean> {
	
	public BoolNode(boolean value, int start, int end) {
		super(start, end);
		this.setValue(value);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.BOOL;
	}
}
