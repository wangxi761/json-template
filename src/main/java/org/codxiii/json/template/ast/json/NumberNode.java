package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

public class NumberNode extends JsonTemplateNode<Number> {
	
	public NumberNode(Number num, String type, int start, int end) {
		super(type, start, end);
		this.setValue(num);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.NUMBER;
	}
}
