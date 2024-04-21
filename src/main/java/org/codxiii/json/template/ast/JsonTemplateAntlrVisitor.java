package org.codxiii.json.template.ast;


import org.codxiii.json.template.ast.json.*;
import org.codxiii.json.template.parser.JsonTemplateParserBaseVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.codxiii.json.template.parser.JsonTemplateParser.*;

public class JsonTemplateAntlrVisitor extends JsonTemplateParserBaseVisitor<JsonTemplateNode<?>> {
	@Override
	public JsonTemplateNode<?> visitObj(ObjContext ctx) {
		ObjectNode objectNode = new ObjectNode(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		for (PairContext pairContext : ctx.pair()) {
			JsonTemplateNode<?> jsonTemplateNode = visitStr(pairContext.str());
			JsonTemplateNode<?> value = visit(pairContext.value());
			objectNode.put((TextNode) jsonTemplateNode, value);
		}
		return objectNode;
	}
	
	@Override
	public JsonTemplateNode<?> visitArr(ArrContext ctx) {
		ArrayNode arrayNode = new ArrayNode(JsonTemplateNodeType.ARRAY.name(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		for (ValueContext valueContext : ctx.value()) {
			JsonTemplateNode<?> value = visit(valueContext);
			arrayNode.add(value);
		}
		return arrayNode;
	}
	
	@Override
	public JsonTemplateNode<?> visitStr(StrContext ctx) {
		List<JsonTemplateNode<?>> nodes = new ArrayList<>();
		boolean pureText = true;
		for (String_contentContext stringContext : ctx.string_content()) {
			JsonTemplateNode<?> node = this.visitString_content(stringContext);
			nodes.add(node);
			if (node.getNodeType() != JsonTemplateNodeType.TEXT) {
				pureText = false;
			}
		}
		if (pureText) {
			String text = nodes.stream().map(JsonTemplateNode::toRawString).collect(Collectors.joining());
			return new TextNode(text, ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		}
		return new TextInterpolationNode(nodes, ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		
	}
	
	@Override
	public JsonTemplateNode<?> visitString_content(String_contentContext ctx) {
		if (ctx.TEXT() == null) {
			return visitVar(ctx.var());
		}
		return new TextNode(ctx.getText(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
	}
	
	@Override
	public JsonTemplateNode<?> visitNum(NumContext ctx) {
		String text = ctx.getText();
		if (text.contains(".")) {
			double value = Double.parseDouble(text);
			return new NumberNode(value, ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		} else {
			long value = Long.parseLong(text);
			return new NumberNode(value, ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
		}
	}
	
	@Override
	public JsonTemplateNode<?> visitVar(VarContext ctx) {
		return new VarNode(ctx.VARNAME().getText(), ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
	}
	
	@Override
	public JsonTemplateNode<?> visitBool(BoolContext ctx) {
		return new BoolNode(ctx.TRUE() != null, ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
	}
	
	@Override
	public JsonTemplateNode<?> visitNull(NullContext ctx) {
		return new NullNode(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
	}
}
