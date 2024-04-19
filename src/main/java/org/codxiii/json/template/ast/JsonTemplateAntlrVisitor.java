package org.codxiii.json.template.ast;


import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.ast.json.*;
import org.codxiii.json.template.parser.JSONBaseVisitor;
import org.codxiii.json.template.parser.JSONParser;

public class JsonTemplateAntlrVisitor extends JSONBaseVisitor<JsonTemplateNode<?>> {
	@Override
	public JsonTemplateNode<?> visitObj(JSONParser.ObjContext ctx) {
		ObjectNode objectNode = new ObjectNode(JsonTemplateNodeType.OBJECT.name(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		
		for (JSONParser.PairContext pairContext : ctx.pair()) {
			String key = pairContext.STRING().getText();
			JsonTemplateNode<?> value = visit(pairContext.value());
			objectNode.put(key, value);
		}
		
		return objectNode;
	}
	
	@Override
	public JsonTemplateNode<?> visitArr(JSONParser.ArrContext ctx) {
		ArrayNode arrayNode = new ArrayNode(JsonTemplateNodeType.ARRAY.name(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		
		for (JSONParser.ValueContext valueContext : ctx.value()) {
			JsonTemplateNode<?> value = visit(valueContext);
			arrayNode.add(value);
		}
		
		return arrayNode;
	}
	
	@Override
	public JsonTemplateNode<?> visitStr(JSONParser.StrContext ctx) {
		return new TextNode(ctx.STRING().getText(), JsonTemplateNodeType.TEXT.name(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
	}
	
	@Override
	public JsonTemplateNode<?> visitNum(JSONParser.NumContext ctx) {
		String text = ctx.getText();
		if (text.contains(".")) {
			double value = Double.parseDouble(text);
			return new NumberNode(value, JsonTemplateNodeType.NUMBER.name(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		} else {
			long value = Long.parseLong(text);
			return new NumberNode(value, JsonTemplateNodeType.NUMBER.name(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		}
	}
	
	@Override
	public JsonTemplateNode<?> visitVar(JSONParser.VarContext ctx) {
		return new VarNode(ctx.VAR().getText(), JsonTemplateNodeType.VAR.name(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
	}
	
	@Override
	public JsonTemplateNode<?> visitBool(JSONParser.BoolContext ctx) {
		return new BoolNode(ctx.TRUE() != null, JsonTemplateNodeType.BOOL.name(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
	}
	
	@Override
	public JsonTemplateNode<?> visitNull(JSONParser.NullContext ctx) {
		return new NullNode(JsonTemplateNodeType.NULL.name(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
	}
}
