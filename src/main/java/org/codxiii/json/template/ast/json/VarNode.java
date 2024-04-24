package org.codxiii.json.template.ast.json;

import lombok.Getter;
import lombok.Setter;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.ast.IRender;
import org.codxiii.json.template.ast.expr.InterpolExpr;

import java.util.Map;

@Getter
@Setter
public class VarNode extends JsonTemplateNode<String> implements IRender {
	
	private InterpolExpr interpolExpr;
	
	private boolean isNode;
	
	public VarNode(InterpolExpr expr, boolean isNode, int start, int end) {
		super(start, end);
		this.setValue(expr.getVariableName());
		this.interpolExpr = expr;
		this.isNode = isNode;
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.VAR;
	}
	
	@Override
	public String toRawString() {
		return "${" + interpolExpr.toRawString() + "}";
	}
	
	
	@Override
	public String render(Map<String, Object> binding) {
		return interpolExpr.render(binding);
	}
}
