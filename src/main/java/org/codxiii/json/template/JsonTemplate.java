package org.codxiii.json.template;

import lombok.Data;
import lombok.experimental.Accessors;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codxiii.json.template.ast.JsonTemplateAntlrVisitor;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.format.JsonTemplateFormatter;
import org.codxiii.json.template.format.JsonTemplateFormatterConfig;
import org.codxiii.json.template.parser.JsonTemplateLexer;
import org.codxiii.json.template.parser.JsonTemplateParser;

import java.util.Map;

@Data
@Accessors(chain = true)
public class JsonTemplate {
	
	private JsonTemplateFormatterConfig config;
	
	private boolean renderWithFormat;
	
	public JsonTemplate() {
		this.config = JsonTemplateFormatterConfig.createDefaultConfig();
		this.renderWithFormat = true;
	}
	
	@SuppressWarnings("rawtypes")
	public JsonTemplateNode parse(String source) {
		JsonTemplateParser parser = new JsonTemplateParser(new CommonTokenStream(new JsonTemplateLexer(CharStreams.fromString(source))));
		JsonTemplateAntlrVisitor visitor = new JsonTemplateAntlrVisitor();
		return parser.json_template().accept(visitor);
	}
	
	public String format(String source) {
		return JsonTemplateFormatter.format(source, null, this.config);
	}
	
	public String render(String source, Map<String, Object> variables) {
		if (!this.renderWithFormat) {
			config.setMinimize(true);
		}
		return JsonTemplateFormatter.format(source, variables, this.config);
	}
	
	
}
