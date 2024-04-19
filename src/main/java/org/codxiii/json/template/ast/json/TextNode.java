package org.codxiii.json.template.ast.json;

import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

public class TextNode extends JsonTemplateNode<String> {
	public TextNode(String value, String type, int start, int end) {
		super(type, start, end);
		this.setValue(value);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.TEXT;
	}
}
