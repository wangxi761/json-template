package org.codxiii.json.template.format;

import lombok.SneakyThrows;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codxiii.json.template.ast.JsonTemplateAntlrVisitor;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.parser.JsonTemplateLexer;
import org.codxiii.json.template.parser.JsonTemplateParser;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;

public class JsonTemplateFormatter {
	private JsonTemplateParser parser;
	private JsonTemplateNode<?> root;
	private JsonTemplateFormatterConfig config;
	
	public JsonTemplateFormatter(JsonTemplateFormatterConfig config) {
		this.config = config;
	}
	
	public void parse(String input) {
		parser = new JsonTemplateParser(new CommonTokenStream(new JsonTemplateLexer(CharStreams.fromString(input))));
		JsonTemplateParser.Json_templateContext rootContext = parser.json_template();
		JsonTemplateAntlrVisitor visitor = new JsonTemplateAntlrVisitor();
		root = visitor.visit(rootContext);
	}
	
	
	public void format(JsonTemplateNode<?> node,JsonTemplateFormattingConstraints constraints){
		if (Objects.equals(node.getNodeType(), JsonTemplateNodeType.ARRAY)) {
		
		} else if (Objects.equals(node.getNodeType(), JsonTemplateNodeType.OBJECT)) {
		
		} else {
		
		}
	}

}
