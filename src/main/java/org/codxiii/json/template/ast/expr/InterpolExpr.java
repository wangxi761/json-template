package org.codxiii.json.template.ast.expr;

import lombok.Data;
import lombok.SneakyThrows;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.codxiii.json.template.ast.IRender;
import org.codxiii.json.template.ast.IString;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.parser.JsonTemplateParser;
import org.codxiii.json.template.util.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.codxiii.json.template.parser.JsonTemplateParser.*;

@Data
public class InterpolExpr implements IRender, IString {
	
	private String variableName;
	private List<AccessorExpr> accessors = new ArrayList<>();
	private String defaultValue;
	private JsonTemplateNodeType nodeType;
	private boolean isNode;
	
	@Override
	@SneakyThrows
	public String render(Map<String, Object> binding) {
		Object value = this.toRawString();
		
		boolean hasKey = true;
		if (binding.containsKey(variableName)) {
			Object obj = binding.get(variableName);
			for (AccessorExpr accessor : accessors) {
				if (obj == null) {
					if (accessor.isNullSafety()) {
						hasKey = false;
						break;
					} else {
						throw new NoSuchFieldException(String.format("No accessible field/method %s", accessor.getVariableName()));
					}
				}
				obj = BeanUtils.getProperty(obj, accessor.getVariableName());
			}
			if (hasKey) {
				value = obj;
			}
		} else {
			hasKey = false;
		}
		
		if (!hasKey && defaultValue != null) {
			value = defaultValue;
		}
		
		// if expr in TextInterpolationNode, return value as raw string
		if (!isNode) {
			return value.toString();
		}
		// TODO:    expr is a node, return value as json string ( object array number boolean null text)
		//          need to implement a json serialization tool here
		//          object  ->  {"key1": "value1", "key2": "value2"}
		//          array   ->  ["value1", "value2"]
		//          number  ->  123
		//          boolean ->  true
		//          null    ->  null
		//          text    ->  "value"
		if (nodeType == null || nodeType == JsonTemplateNodeType.BOOL || nodeType == JsonTemplateNodeType.NUMBER) {
			return value.toString();
		} else if (nodeType == JsonTemplateNodeType.TEXT) {
			return "\"" + value + "\"";
		} else if (nodeType == JsonTemplateNodeType.NULL) {
			return "null";
		} else if (nodeType == JsonTemplateNodeType.ARRAY) {
			//TODO: may be error in there
			return "[" + value + "]";
		} else if (nodeType == JsonTemplateNodeType.OBJECT) {
			//TODO: may be error in there
			return "{" + value + "}";
		} else {
			throw new IllegalArgumentException("Unsupported node type: " + nodeType);
		}
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
		return "${" + sb + "}";
	}
	
	public static InterpolExpr toInterpolExpr(VarContext varContext) {
		boolean isNode = true;
		if (!varContext.getParent().isEmpty()) {
			ParserRuleContext parent = varContext.getParent();
			if (parent instanceof String_contentContext) {
				isNode = false;
			}
		}
		
		InterpolExprContext ctx = varContext.interpolExpr();
		InterpolExpr interpolExpr = new InterpolExpr();
		interpolExpr.setVariableName(ctx.VARNAME().getText());
		
		List<AccessorsContext> accessorsContextList = ctx.accessors();
		for (AccessorsContext accessorsContext : accessorsContextList) {
			AccessorExpr expr = new AccessorExpr();
			expr.setVariableName(accessorsContext.VARNAME().getText());
			expr.setNullSafety(accessorsContext.QUESTION_DOT() != null);
			interpolExpr.getAccessors().add(expr);
		}
		
		NullCoalescingOpContext nullCoalescingOpContext = ctx.nullCoalescingOp();
		if (nullCoalescingOpContext != null) {
			TerminalNode text = nullCoalescingOpContext.TEXT();
			interpolExpr.setDefaultValue(text == null ? "" : text.getText());
		}
		
		TypeSpecContext typeSpecContext = ctx.typeSpec();
		if (typeSpecContext != null) {
			String type = typeSpecContext.type().getText().toUpperCase();
			if (type.equals("STRING")) {
				type = "TEXT";
			}
			interpolExpr.setNodeType(JsonTemplateNodeType.valueOf(type));
		}
		
		interpolExpr.setNode(isNode);
		return interpolExpr;
	}
	
	@Data
	public static class AccessorExpr {
		private String variableName;
		private boolean nullSafety;
	}
}
