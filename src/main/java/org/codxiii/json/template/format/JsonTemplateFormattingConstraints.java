package org.codxiii.json.template.format;

import lombok.Data;

@Data
public class JsonTemplateFormattingConstraints {
	
	private int indentLevel = 0;
	
	public JsonTemplateFormattingConstraints copy() {
		JsonTemplateFormattingConstraints copy = new JsonTemplateFormattingConstraints();
		copy.setIndentLevel(getIndentLevel());
		return copy;
	}
}
