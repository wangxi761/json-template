package org.codxiii.json.template.ast.json;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.JsonTemplateNodeType;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class TextInterpolationNode extends TextNode {
	
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
		StringBuilder sb = new StringBuilder();
		for (JsonTemplateNode<?> child : children) {
			sb.append(child.toRawString());
		}
		return sb.toString();
	}
	
	@Override
	public JsonTemplateNodeType getNodeType() {
		return JsonTemplateNodeType.TEXT_INTERPOLATION;
	}
}
