package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

public class NullNode extends JsonTemplateNode<Void> {
	public NullNode(int start, int end) {
		super(start, end);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.NULL;
	}
	
	@Override
	public String toRawString() {
		return "null";
	}
}
