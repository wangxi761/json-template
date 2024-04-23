package org.codxiii.json.template.ast.json;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;
import org.codxiii.json.template.ast.IRender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Getter
@EqualsAndHashCode(callSuper = true)
public class TextInterpolationNode extends TextNode implements IRender {
	
	private final List<JsonTemplateNode<?>> children;
	
	public TextInterpolationNode(String value, int start, int end) {
		super(value, start, end);
		children = new ArrayList<>();
	}
	
	public TextInterpolationNode(List<JsonTemplateNode<?>> children, int start, int end) {
		super(null, start, end);
		this.children = children;
		this.setValue(toRawString());
	}
	
	public void addChild(JsonTemplateNode<?> child) {
		children.add(child);
	}
	
	@Override
	public String toRawString() {
		return buildString(VarNode::toRawString);
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.TEXT_INTERPOLATION;
	}
	
	@Override
	public String render(Map<String, Object> binding) {
		return buildString(varNode -> varNode.render(binding));
	}
	
	private String buildString(Function<VarNode, String> varNodeProcessor) {
		StringBuilder sb = new StringBuilder();
		for (JsonTemplateNode<?> child : children) {
			if (child.getNodeType() == JsonTemplateNodeType.VAR) {
				sb.append(varNodeProcessor.apply((VarNode) child));
			} else {
				sb.append(child.getValue());
			}
		}
		return "\"" + sb + "\"";
	}
}
