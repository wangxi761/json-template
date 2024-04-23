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
	
	public VarNode(InterpolExpr expr, int start, int end) {
		super(start, end);
		this.setValue(expr.getVariableName());
		this.interpolExpr = expr;
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
		if (!binding.containsKey(this.getValue())) {
			return this.toRawString();
		}
		return binding.get(this.getValue()).toString();
	}
}
