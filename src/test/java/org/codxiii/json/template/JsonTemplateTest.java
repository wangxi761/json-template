package org.codxiii.json.template;

import org.assertj.core.api.BDDAssertions;
import org.codxiii.json.template.ast.JsonTemplateNode;
import org.codxiii.json.template.ast.json.ObjectNode;
import org.codxiii.json.template.ast.json.TextNode;
import org.codxiii.json.template.test.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.InstanceOfAssertFactories.type;


class JsonTemplateTest {
	
	@Test
	void render_replacesVariablesInText() {
		JsonTemplate jsonTemplate = new JsonTemplate();
		Map<String, Object> variables = new HashMap<>();
		variables.put("name", "John");
		String source = "\"Hello ${name}\"";
		String result = jsonTemplate.render(source, variables);
		then(result).isEqualTo("\"Hello John\"");
	}
	
	@Test
	void render_returnsSourceWhenNoVariablesInText() {
		JsonTemplate jsonTemplate = new JsonTemplate();
		Map<String, Object> variables = new HashMap<>();
		String source = "\"Hello World\"";
		String result = jsonTemplate.render(source, variables);
		then(result).isEqualTo("\"Hello World\"");
	}
	
	@Test
	void render_replacesVariablesInObject() {
		JsonTemplate jsonTemplate = new JsonTemplate();
		Map<String, Object> variables = new HashMap<>();
		variables.put("name", "John");
		String source = "{\"name\": \"${name}\"}";
		String result = jsonTemplate.render(source, variables);
		String expected = TestUtil.getResourceFromClassPath("render/render_replacesVariablesInObject.json");
		then(result).isEqualTo(expected);
	}
	
	@Test
	void render_replacesVariablesInObjectWithoutFormat() {
		JsonTemplate jsonTemplate = new JsonTemplate();
		jsonTemplate.getConfig().setAllowTrailingComma(false);
		jsonTemplate.setRenderWithFormat(false);
		Map<String, Object> variables = new HashMap<>();
		variables.put("name", "John");
		String source = "{\"name\": \"${name}\"}";
		String result = jsonTemplate.render(source, variables);
		then(result).isEqualTo("{\"name\": \"John\"}");
	}
	
	@Test
	void format_formatsJsonWithZeroTabSize() {
		JsonTemplate template = new JsonTemplate();
		template.getConfig().setTabSize(0);
		String source = "{\"name\": \"John\", \"age\": 30}";
		String expected = TestUtil.getResourceFromClassPath("format/test_formatsJsonWithZeroTabSize_formatted.json");
		String result = template.format(source);
		then(result).isEqualTo(expected);
	}
	
	@Test
	public void testParseClassic() {
		JsonTemplate template = new JsonTemplate();
		JsonTemplateNode node = template.parse(TestUtil.getResourceFromClassPath("parse/classic.jt"));
		then(node).asInstanceOf(type(ObjectNode.class))
		          .hasFieldOrProperty("value");
	}
	
	@Test
	public void testRenderClassic() {
		JsonTemplate template = new JsonTemplate();
		Map<String, Object> binding = new HashMap<>();
		binding.put("name", "John");
		binding.put("external_description", "This is a description");
		binding.put("version", 30);
		binding.put("status", "active");
		binding.put("info", new LinkedHashMap<String, Object>() {{
			put("name", "John");
			put("age", 30);
			put("loc","USA");
		}});
		String result = template.render(TestUtil.getResourceFromClassPath("parse/classic.jt"), binding);
		then(result).isEqualTo(TestUtil.getResourceFromClassPath("parse/classic_render.jt"));
	}
}