package org.codxiii.json.template.format;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JsonTemplateFormatterConfig {
	private boolean useTab;
	private int tabSize;
}
