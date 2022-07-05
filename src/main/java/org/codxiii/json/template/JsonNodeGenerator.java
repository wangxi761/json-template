package org.codxiii.json.template;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.VarNode;
import org.codxiii.json.template.parser.JSONBaseVisitor;
import org.codxiii.json.template.parser.JSONParser;

import java.util.List;

public class JsonNodeGenerator extends JSONBaseVisitor<JsonNode> {
	@Override
	public JsonNode visitObj(JSONParser.ObjContext ctx) {
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		List<JSONParser.PairContext> pairs = ctx.pair();
		if (pairs != null) {
			for (JSONParser.PairContext pairContext : pairs) {
				node.set(trim(pairContext.STRING().getText()), this.visitValue(pairContext.value()));
			}
		}
		return node;
	}
	
	@Override
	public JsonNode visitArr(JSONParser.ArrContext ctx) {
		ArrayNode node = JsonNodeFactory.instance.arrayNode();
		List<JSONParser.ValueContext> values = ctx.value();
		if (values != null) {
			for (JSONParser.ValueContext value : values) {
				node.add(this.visitValue(value));
			}
		}
		return node;
	}
	
	@Override
	public JsonNode visitStr(JSONParser.StrContext ctx) {
		String text = ctx.getText();
		text = trim(text);
		return JsonNodeFactory.instance.textNode(text);
	}
	
	private String trim(String text) {
		if (text.startsWith("\"") && text.endsWith("\"")) {
			text = text.substring(1, text.length() - 1);
		}
		return text;
	}
	
	@Override
	public JsonNode visitNum(JSONParser.NumContext ctx) {
		String text = ctx.getText();
		if (text.contains(".")) {
			return JsonNodeFactory.instance.numberNode(Double.parseDouble(text));
		} else {
			return JsonNodeFactory.instance.numberNode(Long.parseLong(text));
		}
	}
	
	@Override
	public JsonNode visitVar(JSONParser.VarContext ctx) {
		return new VarNode(ctx.getText());
	}
	
	@Override
	public JsonNode visitBool(JSONParser.BoolContext ctx) {
		if (ctx.TRUE() != null) {
			return JsonNodeFactory.instance.booleanNode(true);
		} else {
			return JsonNodeFactory.instance.booleanNode(false);
		}
	}
	
	@Override
	public JsonNode visitNull(JSONParser.NullContext ctx) {
		return JsonNodeFactory.instance.nullNode();
	}
}
