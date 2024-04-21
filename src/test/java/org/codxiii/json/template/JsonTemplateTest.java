package org.codxiii.json.template;

import org.assertj.core.api.BDDAssertions;
import org.codxiii.json.template.test.TestUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;


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
		jsonTemplate.setRenderWithFormat(false);
		Map<String, Object> variables = new HashMap<>();
		variables.put("name", "John");
		String source = "{\"name\": \"${name}\"}";
		String result = jsonTemplate.render(source, variables);
		then(result).isEqualTo("{\"name\": \"John\"}");
	}
}