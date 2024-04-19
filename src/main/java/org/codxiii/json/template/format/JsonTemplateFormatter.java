package org.codxiii.json.template.format;

import lombok.SneakyThrows;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codxiii.json.template.ast.JsonTemplateAntlrVisitor;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.parser.JSONLexer;
import org.codxiii.json.template.parser.JSONParser;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;

public class JsonTemplateFormatter {
	private JSONParser parser;
	private JsonTemplateNode<?> root;
	private JsonTemplateFormatterConfig config;
	
	public JsonTemplateFormatter(JsonTemplateFormatterConfig config) {
		this.config = config;
	}
	
	public void parse(String input) {
		parser = new JSONParser(new CommonTokenStream(new JSONLexer(CharStreams.fromString(input))));
		JSONParser.JsonContext rootContext = parser.json();
		JsonTemplateAntlrVisitor visitor = new JsonTemplateAntlrVisitor();
		root = visitor.visitJson(rootContext);
	}
	
	
	public void format(JsonTemplateNode<?> node,JsonTemplateFormattingConstraints constraints){
		if (Objects.equals(node.getNodeType(), JsonTemplateNodeType.ARRAY)) {
		
		} else if (Objects.equals(node.getNodeType(), JsonTemplateNodeType.OBJECT)) {
		
		} else {
		
		}
	}
	
	@SneakyThrows
	public static String format(String input) {
		JSONParser parser = new JSONParser(new CommonTokenStream(new JSONLexer(CharStreams.fromString(input))));
		return null;
	}
	
	@SneakyThrows
	public static String format(InputStream input) {
		JSONParser parser = new JSONParser(new CommonTokenStream(new JSONLexer(CharStreams.fromStream(input, StandardCharsets.UTF_8))));
		return null;
	}
	
	@SneakyThrows
	public static String format(Path path) {
		JSONParser parser = new JSONParser(new CommonTokenStream(new JSONLexer(CharStreams.fromPath(path, StandardCharsets.UTF_8))));
		return null;
	}
}
