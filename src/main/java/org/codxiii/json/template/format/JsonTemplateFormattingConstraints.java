package org.codxiii.json.template.format;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

import static org.codxiii.json.template.format.JsonTemplateFormatter.SPACE;
import static org.codxiii.json.template.format.JsonTemplateFormatter.TAB;

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
		this.setMinimize(config.isMinimize());
	}
	
	public JsonTemplateFormattingConstraints copy() {
		JsonTemplateFormattingConstraints copy = new JsonTemplateFormattingConstraints();
		copy.setIndentLevel(getIndentLevel());
		copy.setUseTab(isUseTab());
		copy.setTabSize(getTabSize());
		copy.setNotInObject(isNotInObject());
		copy.setLineSeparator(getLineSeparator());
		copy.setAllowTrailingComma(isAllowTrailingComma());
		copy.setMinimize(isMinimize());
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
		if (this.isMinimize()) {
			return "";
		}
		int level = getIndentLevel() + additionalLevel;
		if (isUseTab()) {
			return TAB.repeat(level);
		} else {
			return SPACE.repeat(level * getTabSize());
		}
	}
	
	public String getLineSeparator() {
		if (this.isMinimize()) {
			return "";
		}
		return lineSeparator;
	}
}
