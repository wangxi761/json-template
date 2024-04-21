package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

public class NumberNode extends JsonTemplateNode<Number> {
	
	public NumberNode(Number num, int start, int end) {
		super(start, end);
		this.setValue(num);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.NUMBER;
	}
}
