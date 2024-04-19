package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

public class NullNode extends JsonTemplateNode<Void> {
	public NullNode(String type, int start, int end) {
		super(type, start, end);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.NULL;
	}
}
