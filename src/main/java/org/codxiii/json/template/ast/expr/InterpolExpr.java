package org.codxiii.json.template.ast.expr;

import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.codxiii.json.template.ast.IString;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.ast.IRender;
import org.codxiii.json.template.ast.json.TextNode;
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
		Object value = this.toRawString();
		
		JsonTemplateNodeType type = Optional.ofNullable(nodeType).orElse(JsonTemplateNodeType.TEXT);
		boolean hasKey = true;
		if (binding.containsKey(variableName)) {
			Object obj = binding.get(variableName);
			for (AccessorExpr accessor : accessors) {
				try {
					obj = getProperty(obj, accessor.getVariableName(), accessor.isNullSafety());
				} catch (Exception e) {
					hasKey = false;
					break;
				}
			}
			if (hasKey) {
				value = obj;
			}
		}else {
			hasKey = false;
		}
		
		if (!hasKey && defaultValue != null) {
			value = defaultValue;
		}
		
		return value.toString();
	}
	
	
	@SneakyThrows
	private Object getProperty(Object obj, String key, boolean nullable) {
		if (obj == null) {
			if (nullable) {
				return null;
			}
			throw new NoPropertyKeyException(key);
		}
		return PropertyUtils.getProperty(obj, key);
		
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
