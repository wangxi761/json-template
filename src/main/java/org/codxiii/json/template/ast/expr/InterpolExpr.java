package org.codxiii.json.template.ast.expr;

import lombok.Data;
import org.codxiii.json.template.ast.IString;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.ast.IRender;
import org.codxiii.json.template.parser.JsonTemplateParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
public class InterpolExpr implements IRender, IString {
	
	private String variableName;
	private List<AccessorExpr> accessors = new ArrayList<>();
	private String defaultValue;
	private JsonTemplateNodeType nodeType;
	
	@Override
	public String render(Map<String, Object> binding) {
		JsonTemplateNodeType type = Optional.ofNullable(nodeType).orElse(JsonTemplateNodeType.TEXT);
		return "";
	}
	
	public String toRawString() {
		StringBuilder sb = new StringBuilder();
		sb.append(variableName);
		for (AccessorExpr accessor : accessors) {
			sb.append(accessor.isNullSafety() ? "?." : ".");
			sb.append(accessor.getVariableName());
		}
		if (defaultValue != null) {
			sb.append("??");
			sb.append(defaultValue);
		}
		if (nodeType != null) {
			sb.append("::");
			sb.append(nodeType.name().toLowerCase());
		}
		return sb.toString();
	}
	
	public static InterpolExpr toInterpolExpr(JsonTemplateParser.InterpolExprContext ctx) {
		InterpolExpr interpolExpr = new InterpolExpr();
		interpolExpr.setVariableName(ctx.VARNAME().getText());
		
		List<JsonTemplateParser.AccessorsContext> accessorsContextList = ctx.accessors();
		for (JsonTemplateParser.AccessorsContext accessorsContext : accessorsContextList) {
			AccessorExpr expr = new AccessorExpr();
			expr.setVariableName(accessorsContext.VARNAME().getText());
			expr.setNullSafety(accessorsContext.QUESTION_DOT() != null);
			interpolExpr.getAccessors().add(expr);
		}
		
		JsonTemplateParser.NullCoalescingOpContext nullCoalescingOpContext = ctx.nullCoalescingOp();
		if (nullCoalescingOpContext != null) {
			interpolExpr.setDefaultValue(nullCoalescingOpContext.TEXT().getText());
		}
		
		JsonTemplateParser.TypeSpecContext typeSpecContext = ctx.typeSpec();
		if (typeSpecContext != null) {
			String type = typeSpecContext.getText().toUpperCase();
			if (type.equals("STRING")) {
				type = "TEXT";
			}
			interpolExpr.setNodeType(JsonTemplateNodeType.valueOf(type));
		}
		return interpolExpr;
	}
	
	@Data
	public static class AccessorExpr {
		private String variableName;
		private boolean nullSafety;
	}
}
