package org.codxiii.json.template.format;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.codxiii.json.template.ast.JsonTemplateAntlrVisitor;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.ast.Renderable;
import org.codxiii.json.template.ast.json.ArrayNode;
import org.codxiii.json.template.ast.json.ObjectNode;
import org.codxiii.json.template.ast.json.TextNode;
import org.codxiii.json.template.parser.JsonTemplateLexer;
import org.codxiii.json.template.parser.JsonTemplateParser;

import java.util.Map;
import java.util.Objects;

public class JsonTemplateFormatter {
	
	public static final String LEFT_BRACE = "{";
	public static final String RIGHT_BRACE = "}";
	public static final String LEFT_BRACKET = "[";
	public static final String RIGHT_BRACKET = "]";
	public static final String COMMA = ",";
	public static final String COLON = ":";
	public static final String SPACE = " ";
	public static final String TAB = "\t";
	
	private JsonTemplateNode<?> root;
	private final JsonTemplateFormatterConfig config;
	private final Map<String, Object> binding;
	
	public JsonTemplateFormatter(JsonTemplateFormatterConfig config, Map<String, Object> binding) {
		this.config = config;
		this.binding = binding;
	}
	
	public void parse(String input) {
		JsonTemplateParser parser = new JsonTemplateParser(new CommonTokenStream(new JsonTemplateLexer(CharStreams.fromString(input))));
		JsonTemplateParser.Json_templateContext rootContext = parser.json_template();
		JsonTemplateAntlrVisitor visitor = new JsonTemplateAntlrVisitor();
		root = visitor.visit(rootContext);
	}
	
	public void format(JsonTemplateNode<?> node, StringBuilder sb) {
		format(node, sb, new JsonTemplateFormattingConstraints(config));
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
			sb.append(LEFT_BRACKET);
			ArrayNode arrayNode = node.toArrayNode();
			if (arrayNode.isEmpty()) {
				sb.append(RIGHT_BRACKET);
				return;
			}
			sb.append(constraints.getLineSeparator());
			for (JsonTemplateNode<?> subNode : arrayNode) {
				format(subNode, sb, subConstraints.setNotInObject(true));
				sb.append(COMMA).append(constraints.getLineSeparator());
			}
			if (!config.isAllowTrailingComma()) {
				sb.deleteCharAt(sb.length() - constraints.getLineSeparator().length() - 1);
			}
			sb.append(constraints.generateIndent()).append(RIGHT_BRACKET);
		} else if (Objects.equals(node.getNodeType(), JsonTemplateNodeType.OBJECT)) {
			sb.append(LEFT_BRACE);
			ObjectNode objectNode = node.toObjectNode();
			if (objectNode.isEmpty()) {
				sb.append(RIGHT_BRACE);
				return;
			}
			sb.append(constraints.getLineSeparator());
			for (Map.Entry<TextNode, JsonTemplateNode<?>> entry : objectNode) {
				sb.append(constraints.generateIndent(1))
				  .append(entry.getKey().toRawString())
				  .append(COLON).append(SPACE);
				format(entry.getValue(), sb, subConstraints.setNotInObject(false));
				sb.append(COMMA).append(constraints.getLineSeparator());
			}
			if (!config.isAllowTrailingComma()) {
				sb.deleteCharAt(sb.length() - constraints.getLineSeparator().length() - 1);
			}
			sb.append(constraints.generateIndent()).append(RIGHT_BRACE);
		} else if (Objects.equals(node.getNodeType(), JsonTemplateNodeType.VAR) || Objects.equals(node.getNodeType(), JsonTemplateNodeType.TEXT_INTERPOLATION)) {
			if (this.binding != null) {
				sb.append(((Renderable) node).render(this.binding));
			} else {
				sb.append(node.toRawString());
			}
		} else {
			sb.append(node.toRawString());
		}
	}
	
	public static String format(String input, Map<String, Object> binding, JsonTemplateFormatterConfig config) {
		JsonTemplateFormatter formatter = new JsonTemplateFormatter(config, binding);
		formatter.parse(input);
		StringBuilder sb = new StringBuilder();
		formatter.format(formatter.root, sb);
		return sb.toString();
	}
	
}
