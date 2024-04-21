package org.codxiii.json.template.format;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JsonTemplateFormattingConstraints {
	
	private int indentLevel;
	private boolean useTab;
	private int tabSize;
	private boolean notInObject;
	
	public JsonTemplateFormattingConstraints copy() {
		JsonTemplateFormattingConstraints copy = new JsonTemplateFormattingConstraints();
		copy.setIndentLevel(getIndentLevel());
		copy.setUseTab(isUseTab());
		copy.setTabSize(getTabSize());
		copy.setNotInObject(isNotInObject());
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
	
	public static class Builder {
		private JsonTemplateFormattingConstraints constraints;
		
		public Builder() {
			constraints = new JsonTemplateFormattingConstraints();
		}
		
		public Builder(JsonTemplateFormatterConfig config) {
			constraints = new JsonTemplateFormattingConstraints();
			constraints.setUseTab(config.isUseTab());
			constraints.setTabSize(config.getTabSize());
		}
		
		public Builder indentLevel(int indentLevel) {
			constraints.setIndentLevel(indentLevel);
			return this;
		}
		
		public Builder useTab(boolean useTab) {
			constraints.setUseTab(useTab);
			return this;
		}
		
		public Builder tabSize(int tabSize) {
			constraints.setTabSize(tabSize);
			return this;
		}
		
		public Builder notInObject(boolean notInObject) {
			constraints.setNotInObject(notInObject);
			return this;
		}
		
		public JsonTemplateFormattingConstraints build() {
			return constraints;
		}
	}
}
