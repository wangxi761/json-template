package org.codxiii.json.template.ast.json;

import lombok.EqualsAndHashCode;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
@EqualsAndHashCode(callSuper = true)
public class TextNode extends JsonTemplateNode<String> {
	public TextNode(String value, int start, int end) {
		super(start, end);
		this.setValue(value);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.TEXT;
	}
	
	@Override
	public String toRawString() {
		return "\"" + getValue() + "\"";
	}
}
