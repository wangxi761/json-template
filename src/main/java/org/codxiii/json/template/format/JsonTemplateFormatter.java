package org.codxiii.json.template.format;

import lombok.SneakyThrows;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codxiii.json.template.ast.JsonTemplateAntlrVisitor;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.ast.json.ArrayNode;
import org.codxiii.json.template.ast.json.ObjectNode;
import org.codxiii.json.template.ast.json.TextNode;
import org.codxiii.json.template.parser.JsonTemplateLexer;
import org.codxiii.json.template.parser.JsonTemplateParser;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;
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
	
	public void format(JsonTemplateNode<?> node, StringBuilder sb) {
		format(node, sb, new JsonTemplateFormattingConstraints.Builder(config).build());
	}
	
	private void format(JsonTemplateNode<?> node, StringBuilder sb, JsonTemplateFormattingConstraints constraints) {
		if (node == null) {
			return;
		}
		if (constraints.isNotInObject()) {
			sb.append(constraints.generateIndent());
		}
		
		JsonTemplateFormattingConstraints subConstraints = constraints.copy().addIndentLevel();
		if (Objects.equals(node.getNodeType(), JsonTemplateNodeType.ARRAY)) {
			sb.append("[");
			ArrayNode arrayNode = node.toArrayNode();
			if (arrayNode.isEmpty()) {
				sb.append("]");
				return;
			}
			sb.append(constraints.getLineSeparator());
			for (JsonTemplateNode<?> subNode : arrayNode) {
				format(subNode, sb, subConstraints.setNotInObject(true));
				sb.append(",").append(constraints.getLineSeparator());
			}
			sb.append(constraints.generateIndent()).append("]");
		} else if (Objects.equals(node.getNodeType(), JsonTemplateNodeType.OBJECT)) {
			sb.append("{");
			ObjectNode objectNode = node.toObjectNode();
			if (objectNode.isEmpty()) {
				sb.append("}");
				return;
			}
			sb.append(constraints.getLineSeparator());
			for (Map.Entry<TextNode, JsonTemplateNode<?>> entry : objectNode) {
				sb.append(constraints.generateIndent(1))
				  .append(entry.getKey().toRawString())
				  .append(": ");
				format(entry.getValue(), sb, subConstraints.setNotInObject(false));
				sb.append(",").append(constraints.getLineSeparator());
			}
			sb.append(constraints.generateIndent()).append("}");
		} else if (Objects.equals(node.getNodeType(), JsonTemplateNodeType.TEXT) || Objects.equals(node.getNodeType(), JsonTemplateNodeType.TEXT_INTERPOLATION)) {
			sb.append(node.toRawString());
		} else {
			sb.append(node.toRawString());
		}
	}
	
	public static String format(String input, JsonTemplateFormatterConfig config) {
		JsonTemplateFormatter formatter = new JsonTemplateFormatter(config);
		formatter.parse(input);
		StringBuilder sb = new StringBuilder();
		formatter.format(formatter.root, sb);
		return sb.toString();
	}
	
	public static JsonTemplateFormatterConfig createDefaultConfig() {
		return new JsonTemplateFormatterConfig()
			.setTabSize(4)
			.setUseTab(false);
	}
	
}
