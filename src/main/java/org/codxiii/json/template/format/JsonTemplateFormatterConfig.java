package org.codxiii.json.template.format;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JsonTemplateFormatterConfig {
	private boolean useTab;
	private int tabSize;
	private boolean allowTrailingComma;
	
	public static JsonTemplateFormatterConfig createDefaultConfig() {
		return new JsonTemplateFormatterConfig()
			.setTabSize(4)
			.setUseTab(false)
			.setAllowTrailingComma(true);
	}
	
}
