package org.codxiii.json.template.format;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@NoArgsConstructor
public class JsonTemplateFormattingConstraints extends JsonTemplateFormatterConfig {
	
	private int indentLevel;
	private boolean notInObject;
	private String lineSeparator;
	
	public JsonTemplateFormattingConstraints(JsonTemplateFormatterConfig config) {
		this.setUseTab(config.isUseTab());
		this.setTabSize(config.getTabSize());
		this.setAllowTrailingComma(config.isAllowTrailingComma());
		this.setIndentLevel(0);
		this.setNotInObject(false);
		this.setLineSeparator(System.lineSeparator());
	}
	
	public JsonTemplateFormattingConstraints copy() {
		JsonTemplateFormattingConstraints copy = new JsonTemplateFormattingConstraints();
		copy.setIndentLevel(getIndentLevel());
		copy.setUseTab(isUseTab());
		copy.setTabSize(getTabSize());
		copy.setNotInObject(isNotInObject());
		copy.setLineSeparator(getLineSeparator());
		copy.setAllowTrailingComma(isAllowTrailingComma());
		return copy;
	}
	
	public JsonTemplateFormattingConstraints addIndentLevel() {
		setIndentLevel(getIndentLevel() + 1);
		return this;
	}
	
	
	public String generateIndent() {
		return generateIndent(0);
	}
	
	public String generateIndent(int additionalLevel) {
		int level = getIndentLevel() + additionalLevel;
		if (isUseTab()) {
			return "\t".repeat(level);
		} else {
			return " ".repeat(level * getTabSize());
		}
	}
	
}
